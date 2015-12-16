package fj;

import java.util.List;
import java.util.Map;

import fj.fjalg.external.FJAlgMatcher;
import fj.fjalg.shared.FJAlgQuery;
import fj.fjalg.shared.GFJAlg;
import library.Zero;

/**
 * Evaluation: t -> t' ==================
 *
 */
public interface Eval<Term, Klass, Ctr, Method, Prog> extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, Term> {
	GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog> alg();

	Fields<Term, Klass, Ctr, Method, Prog> fields();

	FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Term> matcher();

	IsVal<Term, Klass, Ctr, Method, Prog> isVal();

	Subtype<Term, Klass, Ctr, Method, Prog> subtype();

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
								? alg().New(D, vs) : m().empty())
						.otherwise(() -> m().empty())
						.visitTerm(t)
				: alg().Cast(D, visitTerm(t)); // E-Cast
	}

	/**
	 * fields(C) = [C f] 
	 * --------------------- (E-ProjNew) 
	 * (new C([v])).fi -> vi
	 */
	default Term New(String c, List<Term> vs) {
		fields().visitKlass(c);
	}

	default Term Var(String p1) {
		// TODO Auto-generated method stub
		return FJAlgQuery.super.Var(p1);
	}

	/**
	 * t -> t' ----------- (E-Field) t.f -> t'.f
	 */
	default Term FieldAccess(Term t, String f) {
		return alg().FieldAccess(visitTerm(t), f);
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
		return isVal().visitTerm(t) ? ts.stream().allMatch(isVal()::)
				: alg().MethodCall(visitTerm(t), m, ts); // E-Invk-Recv
				
	}
}