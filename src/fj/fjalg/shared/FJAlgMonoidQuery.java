package fj.fjalg.shared;

public interface FJAlgMonoidQuery<Term, Klass, Ctr, Method, Prog, O> extends fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, O, O, O, O, O> {
	library.Monoid<O> m();

	default O Cast(java.lang.String p1, Term p2) {
		return visitTerm(p2);
	}

	default O New(java.lang.String p1, java.util.List<Term> p2) {
		return p2.stream().map(_p2 -> visitTerm(_p2)).reduce(m().empty(), m()::join);
	}

	default O Program(java.util.List<Klass> p1, Term p2) {
		return java.util.stream.Stream.of(p1.stream().map(_p1 -> visitKlass(_p1)).reduce(m().empty(), m()::join), visitTerm(p2)).reduce(m().empty(), m()::join);
	}

	default O Var(java.lang.String p1) {
		return m().empty();
	}

	default O Constructor(java.lang.String p1, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p2, java.util.List<java.lang.String> p3, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p4) {
		return m().empty();
	}

	default O Class(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p3, Ctr p4, java.util.List<Method> p5) {
		return java.util.stream.Stream.of(visitCtr(p4), p5.stream().map(_p5 -> visitMethod(_p5)).reduce(m().empty(), m()::join)).reduce(m().empty(), m()::join);
	}

	default O Object() {
		return m().empty();
	}

	default O Method(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p3, Term p4) {
		return visitTerm(p4);
	}

	default O FieldAccess(Term p1, java.lang.String p2) {
		return visitTerm(p1);
	}

	default O MethodCall(Term p1, java.lang.String p2, java.util.List<Term> p3) {
		return java.util.stream.Stream.of(visitTerm(p1), p3.stream().map(_p3 -> visitTerm(_p3)).reduce(m().empty(), m()::join)).reduce(m().empty(), m()::join);
	}
}
