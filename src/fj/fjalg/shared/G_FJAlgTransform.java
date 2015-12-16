package fj.fjalg.shared;

public interface G_FJAlgTransform<O, Term, Klass, Ctr, Method, Prog> extends fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, java.util.function.Function<O, Term>, java.util.function.Function<O, Klass>, java.util.function.Function<O, Ctr>, java.util.function.Function<O, Method>, java.util.function.Function<O, Prog>> {
	fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog> alg();

	default java.util.function.Function<O, Term> Cast(java.lang.String p1, Term p2) {
		return c -> alg().Cast(p1, visitTerm(p2).apply(c));
	}

	default java.util.function.Function<O, Term> New(java.lang.String p1, java.util.List<Term> p2) {
		return c -> alg().New(p1, p2.stream().map(_p2 -> visitTerm(_p2).apply(c)).collect(java.util.stream.Collectors.toList()));
	}

	default java.util.function.Function<O, Prog> Program(java.util.List<Klass> p1, Term p2) {
		return c -> alg().Program(p1.stream().map(_p1 -> visitKlass(_p1).apply(c)).collect(java.util.stream.Collectors.toList()), visitTerm(p2).apply(c));
	}

	default java.util.function.Function<O, Term> Var(java.lang.String p1) {
		return c -> alg().Var(p1);
	}

	default java.util.function.Function<O, Ctr> Constructor(java.lang.String p1, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p2, java.util.List<java.lang.String> p3, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p4) {
		return c -> alg().Constructor(p1, p2, p3, p4);
	}

	default java.util.function.Function<O, Klass> Class(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p3, Ctr p4, java.util.List<Method> p5) {
		return c -> alg().Class(p1, p2, p3, visitCtr(p4).apply(c), p5.stream().map(_p5 -> visitMethod(_p5).apply(c)).collect(java.util.stream.Collectors.toList()));
	}

	default java.util.function.Function<O, Klass> Object() {
		return c -> alg().Object();
	}

	default java.util.function.Function<O, Method> Method(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String,java.lang.String>> p3, Term p4) {
		return c -> alg().Method(p1, p2, p3, visitTerm(p4).apply(c));
	}

	default java.util.function.Function<O, Term> FieldAccess(Term p1, java.lang.String p2) {
		return c -> alg().FieldAccess(visitTerm(p1).apply(c), p2);
	}

	default java.util.function.Function<O, Term> MethodCall(Term p1, java.lang.String p2, java.util.List<Term> p3) {
		return c -> alg().MethodCall(visitTerm(p1).apply(c), p2, p3.stream().map(_p3 -> visitTerm(_p3).apply(c)).collect(java.util.stream.Collectors.toList()));
	}
}