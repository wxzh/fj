package fj;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fj.fjalg.external.FJAlgMatcher;
import fj.fjalg.shared.GFJAlg;
import library.Tuple2;

interface ITypeof {
	String typeof(Map<String, String> Gamma);
}

interface TCheckMethod {
	void tcheckMethod(String c);
}

interface TCheckCtr {
	void tcheckCtr(List<Tuple2<String, String>> dgs, List<Tuple2<String, String>> cfs);
}

class TypeError extends RuntimeException {
	private static final long serialVersionUID = 1L;
}

public interface Typeof<Term, Klass, Ctr, Method, Prog>
	extends GFJAlg<Term, Klass, Ctr, Method, Prog, ITypeof, Void, TCheckCtr, TCheckMethod, String> {
	Fields<Term, Klass, Ctr, Method, Prog> fields();
	Subtype<Term, Klass, Ctr, Method, Prog> subtype();
	Override<Term, Klass, Ctr, Method, Prog> override();
	Mtype<Term, Klass, Ctr, Method, Prog> mtype();
	Map<String, Klass> classTable();
	FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Klass> matcher();

	/**
	 * x : C \in Gamma
	 * --------------- (T-Var)
	 * Gamma |- x : C
	 */
	default ITypeof Var(String x) {
		return Gamma -> {
			if (Gamma.containsKey(x))
				return Gamma.get(x);
			throw new TypeError();
		};
	}

	default Void Object() {
		return null;
	}

	/**
	 *       fields(C) = [D f]
	 * Gamma |- [t] : [C]  [C] <: [D]
	 * ------------------------------ (T-New)
	 *    Gamma |- new C([t]) : C
	 */
	default ITypeof New(String C0, List<Term> ts) {
		return Gamma -> {
			List<Tuple2<String, String>> fields = fields().visitKlass(classTable().get(C0));
			if (ts.size() == fields.size()
					&& IntStream.range(0, ts.size()).allMatch(i -> {
						Klass C = classTable().get(visitTerm(ts.get(i)).typeof(Gamma));
						Klass D = classTable().get(fields.get(i)._1);
						return subtype().visitKlass(C).subtype(D);
					})) {
					return C0;
			}
			throw new TypeError();
		};
	}

	/**
	 *       Gamma |- t0 : C0
	 *    mtype(m, C0) = [D] -> C
	 * Gamma |- [t] : [C]  [C] <: [D]
	 * ------------------------------ (T-Invk)
	 *   Gamma |- t.m([t]) : C
	 */
	default ITypeof MethodCall(Term t0, String m, List<Term> ts) {
		return Gamma -> {
			Klass C0 = classTable().get(visitTerm(t0).typeof(Gamma));
			MethodType mtype = mtype().visitKlass(C0).mtype(m);
			List<String> Ds = mtype.tyParams;
			if (ts.size() == Ds.size()
					&& IntStream.range(0, ts.size()).allMatch(i -> {
						Klass C = classTable().get(visitTerm(ts.get(i)).typeof(Gamma));
						Klass D = classTable().get(Ds.get(i));
						return subtype().visitKlass(C).subtype(D);
					})) {
				return mtype.tyReturn;
			}
			throw new TypeError();
		};
	}

	/**
	 * Gamma |- t0 : C0  fields(C0) = [C f]
	 * ------------------------------------ (T-Field)
	 *    Gamma |- t0.fi : Ci
	 */
	default ITypeof FieldAccess(Term t, String f) {
		return Gamma -> {
			Klass C = classTable().get(visitTerm(t).typeof(Gamma));
			for (Tuple2<String, String> field : fields().visitKlass(C))
				if (field._2.equals(f))
					return field._1;
			throw new TypeError();
		};
	}

	/**
	 * Gamma |- t0 : D  D <: C
	 * ------------------------- (T-UCast)
	 *   Gamma |- (C) t0 : C
	 *
	 * Gamma |- t0 : D  C <: D  C /= D
	 * ------------------------------- (T-DCast)
	 *   Gamma |- (C) t0 : C
	 *
	 * Gamma |- t0 : D  C /<: D  D /<: C
	 *         stupid warning
	 * ------------------------------- (T-DCast)
	 *   Gamma |- (C) t0 : C
	 */
	default ITypeof Cast(String c, Term t) {
		return Gamma -> {
			String d = visitTerm(t).typeof(Gamma);
			Klass C = classTable().get(c);
			Klass D = classTable().get(d);
			if (subtype().visitKlass(D).subtype(C)) // T-UCast
				return c;
			else if (subtype().visitKlass(C).subtype(D)) {
				if (!c.equals(d)) // T-DCast
					return c;
				throw new TypeError();
			}
			else { // T-DCast
				System.out.println("stupid warning");
				return c;
			}
		};
	}

	default String Program(List<Klass> Cs, Term t) {
		Cs.forEach(this::visitKlass);
		return visitTerm(t).typeof(Collections.emptyMap());
	}

	/**
	 * Method typing
	 *
	 * [x:C], this:C |- t0 : E0   E0 <: C0
	 * CT(C) = class C extends D {...}
	 *     override(m, D, [C] -> C0)
	 * ----------------------------------
	 * C0 m([C x]) {return t0;}  OK in C
	 */
	default TCheckMethod Method(String c0, String m, List<Tuple2<String, String>> params, Term t0) {
		return c -> {
			Map<String, String> Gamma = Stream.concat(params.stream(), Stream.of(new Tuple2<>(c, "this")))
					.collect(Collectors.toMap(pr -> pr._2, pr -> pr._1));
			Klass E0 = classTable().get(visitTerm(t0).typeof(Gamma));
			Klass C0 = classTable().get(c0);
			if (subtype().visitKlass(E0).subtype(C0)) {
				Klass C = classTable().get(c);
				Klass D = matcher().Class(self -> d -> fs -> ctr -> ms -> classTable().get(d))
						.otherwise(() -> null).visitKlass(C);
				if (override().override(m, D, params.stream().map(pr -> classTable().get(pr._1)).collect(Collectors.toList()), classTable().get(c0)))
					return;
			}
			throw new TypeError();
		};
	}

	/**
	 * Class typing
	 *
	 * K = C([D g] ++ [C f]) {super([g]); [this.f = f;]}
	 * fields(D) = [D g]    [M] OK in C
	 * -----------------------------------------------
	 *    class C extends D {[C f;] K [M]}  OK
	 */
	default Void Class(String c, String d, List<Tuple2<String, String>> cfs, Ctr K, List<Method> methods) {
		Klass D = classTable().get(d);
		visitCtr(K).tcheckCtr(fields().visitKlass(D), cfs);
		methods.forEach(m -> visitMethod(m).tcheckMethod(c));
		return null;
	}

	default TCheckCtr Constructor(String c, List<Tuple2<String, String>> params, List<String> gs,
			List<String> fs) {
		//TODO
		return (dgs, cfs) -> {
			if (cfs.size() == fs.size()
					&& IntStream.range(0, fs.size()).allMatch(i -> cfs.get(i)._2.equals(fs.get(i)))) // assignments are fields
				return;
			throw new TypeError();
		};
	}
}