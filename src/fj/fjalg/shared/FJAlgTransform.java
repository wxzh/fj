package fj.fjalg.shared;

public interface FJAlgTransform<Term, Klass, Ctr, Method, Prog> extends fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog> {
	fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog> alg();

	default Term Cast(java.lang.String p1, Term p2) {
		return alg().Cast(p1, visitTerm(p2));
	}

	default Term New(java.lang.String p1, java.util.List<Term> p2) {
		return alg().New(p1, p2.stream().map(_p2 -> visitTerm(_p2)).collect(java.util.stream.Collectors.toList()));
	}

	default Prog Program(java.util.List<Klass> p1, Term p2) {
		return alg().Program(p1.stream().map(_p1 -> visitKlass(_p1)).collect(java.util.stream.Collectors.toList()), visitTerm(p2));
	}

	default Term Var(java.lang.String p1) {
		return alg().Var(p1);
	}

	default Ctr Constructor(java.lang.String p1, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p2, java.util.List<java.lang.String> p3, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p4) {
		return alg().Constructor(p1, p2, p3, p4);
	}

	default Klass Class(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p3, Ctr p4, java.util.List<Method> p5) {
		return alg().Class(p1, p2, p3, visitCtr(p4), p5.stream().map(_p5 -> visitMethod(_p5)).collect(java.util.stream.Collectors.toList()));
	}

	default Klass Object() {
		return alg().Object();
	}

	default Method Method(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p3, Term p4) {
		return alg().Method(p1, p2, p3, visitTerm(p4));
	}

	default Term FieldAccess(Term p1, java.lang.String p2) {
		return alg().FieldAccess(visitTerm(p1), p2);
	}

	default Term MethodCall(Term p1, java.lang.String p2, java.util.List<Term> p3) {
		return alg().MethodCall(visitTerm(p1), p2, p3.stream().map(_p3 -> visitTerm(_p3)).collect(java.util.stream.Collectors.toList()));
	}
}