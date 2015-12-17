package fj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fj.fjalg.external.FJAlgMatcher;
import fj.fjalg.shared.FJAlgQuery;
import fj.fjalg.shared.GFJAlg;
import library.Tuple2;
import library.Zero;

/**
 * Evaluation: t -> t'
 */
public interface Eval1<Term, Klass, Ctr, Method, Prog> extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, Term> {
	GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog> alg();

	Fields<Term, Klass, Ctr, Method, Prog> fields();

	FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Term> matcher();

	IsVal<Term, Klass, Ctr, Method, Prog> isVal();

	Mbody<Term, Klass, Ctr, Method, Prog> mbody();

	Subtype<Term, Klass, Ctr, Method, Prog> subtype();

	Subst<Term, Klass, Ctr, Method, Prog> subst();

	Map<String, Klass> classTable();

	default Zero<Term> m() {
		throw new RuntimeException();
	}

	/**
	 *          C <: D
	 * ---------------------------- (E-CastNew)
	 * (D)(new C([v]) -> new C([v])
	 *
	 *    t -> t'
	 * -------------- (E-Cast)
	 * (C) t -> (C) t'
	 */
	default Term Cast(String D, Term t) {
		return isVal().visitTerm(t)
				? matcher() // E-CastNew
						.New(C -> vs -> subtype() .visitKlass(classTable().get(C)).subtype(classTable().get(D))
								? alg().New(C, vs) : m().empty())
						.otherwise(() -> m().empty())
						.visitTerm(t)
				: alg().Cast(D, visitTerm(t)); // E-Cast
	}

	/**
	 *  ti -> ti'
	 *  ---------------------------------------------------  (E-New-Arg)
	 *  new C([v] ++ ti ++ [t]) -> new C([v] ++ ti' ++ [t])
	 */
	default Term New(String c, List<Term> ts) {
		System.out.println("called");
		return IntStream.range(0, ts.size()).boxed().filter(i -> !isVal().visitTerm(ts.get(i))).findFirst()
				.map(i -> {
					List<Term> ts2 = new ArrayList<>(ts);
					ts2.set(i, visitTerm(ts.get(i)));
					return alg().New(c, ts2); })
				.orElseGet(() -> alg().New(c, ts));
	}

	/**
	 * fields(C) = [C f]
	 * --------------------- (E-ProjNew)
	 * (new C([v])).fi -> vi
	 *
	 * t -> t'
	 * ----------- (E-Field)
	 * t.f -> t'.f
	 */
	default Term FieldAccess(Term t, String f) {
		return isVal().visitTerm(t)
				? matcher()
						.New(C -> vs -> {
							List<Tuple2<String, String>> fields = fields().visitKlass(classTable().get(C));
							return IntStream.range(0, vs.size()).boxed().filter(i -> fields.get(i)._2.equals(f)).findFirst()
									.map(vs::get)
									.orElseGet(() -> m().empty());
						}).otherwise(() -> m().empty())
						.visitTerm(t)
				: alg().FieldAccess(visitTerm(t), f);
	}


	/**
	 *                mbody(m,C) = ([x],t)
	 * -------------------------------------------------------- (E-InvkNew)
	 * (new C([v]).m([u]) -> {[x] -> [u], this -> new C([v])}t
	 *
	 *        t -> t'
	 * --------------------- (E-Invk-Recv)
	 *  t.m([t]) -> t'.m([t])
	 *
	 *                t -> t'
	 * -------------------------------------- (E-Invk-Arg)
	 *  v.m([v]++t++[t]) -> v.m([v]++t'++[t])
	 */
	default Term MethodCall(Term t, String m, List<Term> ts) {
		return isVal().visitTerm(t) ?
				IntStream.range(0, ts.size()).boxed().filter(i -> !isVal().visitTerm(ts.get(i))).findFirst()
				.map(i -> { // E-Invk-Arg
					List<Term> ts2 = new ArrayList<>(ts);
					ts2.set(i, visitTerm(ts.get(i)));
					return alg().MethodCall(t, m, ts2);})
				.orElseGet(() -> matcher() // E-InvkNew
						.New(c -> vs -> {
							Klass C = classTable().get(c);
							MethodBody<Term> mbody = mbody().visitKlass(C).mbody(m);
							Map<String, Term> ctx = new HashMap<>();
							ctx.putAll(IntStream.range(0, ts.size()).boxed().collect(Collectors.toMap(i -> mbody.params.get(i), i -> ts.get(i))));
							ctx.put("this", t);
							return subst().visitTerm(mbody.body).subst(ctx);})
						.otherwise(() -> m().empty())
						.visitTerm(t))
				: alg().MethodCall(visitTerm(t), m, ts); // E-Invk-Recv
	}
}