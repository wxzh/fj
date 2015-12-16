package fj.fjalg.shared;

public interface FJAlgQuery<Term, Klass, Ctr, Method, Prog, O> extends fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, O, O, O, O, O> {
	library.Zero<O> m();

	default O Cast(java.lang.String p1, Term p2) {
		return m().empty();
	}

	default O New(java.lang.String p1, java.util.List<Term> p2) {
		return m().empty();
	}

	default O Program(java.util.List<Klass> p1, Term p2) {
		return m().empty();
	}

	default O Var(java.lang.String p1) {
		return m().empty();
	}

	default O Constructor(java.lang.String p1, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p2, java.util.List<java.lang.String> p3, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p4) {
		return m().empty();
	}

	default O Class(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p3, Ctr p4, java.util.List<Method> p5) {
		return m().empty();
	}

	default O Object() {
		return m().empty();
	}

	default O Method(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p3, Term p4) {
		return m().empty();
	}

	default O FieldAccess(Term p1, java.lang.String p2) {
		return m().empty();
	}

	default O MethodCall(Term p1, java.lang.String p2, java.util.List<Term> p3) {
		return m().empty();
	}
}