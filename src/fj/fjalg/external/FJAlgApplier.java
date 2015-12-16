package fj.fjalg.external;

public interface FJAlgApplier<ITerm, IKlass, ICtr, IMethod, IProg, O> extends fj.fjalg.shared.GFJAlg<ITerm, IKlass, ICtr, IMethod, IProg, O, O, O, O, O> {
	FJAlgMapper<ITerm, IKlass, ICtr, IMethod, IProg, O> mapper();

	default O Cast(java.lang.String p1, ITerm p2) {
		return mapper().CastMapper().apply(p1).apply(p2);
	}

	default O New(java.lang.String p1, java.util.List<ITerm> p2) {
		return mapper().NewMapper().apply(p1).apply(p2);
	}

	default O Program(java.util.List<IKlass> p1, ITerm p2) {
		return mapper().ProgramMapper().apply(p1).apply(p2);
	}

	default O Var(java.lang.String p1) {
		return mapper().VarMapper().apply(p1);
	}

	default O Constructor(java.lang.String p1, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p2, java.util.List<java.lang.String> p3, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p4) {
		return mapper().ConstructorMapper().apply(p1).apply(p2).apply(p3).apply(p4);
	}

	default O Class(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, ICtr p4, java.util.List<IMethod> p5) {
		return mapper().ClassMapper().apply(p1).apply(p2).apply(p3).apply(p4).apply(p5);
	}

	default O Object() {
		return mapper().ObjectMapper().get();
	}

	default O Method(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, ITerm p4) {
		return mapper().MethodMapper().apply(p1).apply(p2).apply(p3).apply(p4);
	}

	default O FieldAccess(ITerm p1, java.lang.String p2) {
		return mapper().FieldAccessMapper().apply(p1).apply(p2);
	}

	default O MethodCall(ITerm p1, java.lang.String p2, java.util.List<ITerm> p3) {
		return mapper().MethodCallMapper().apply(p1).apply(p2).apply(p3);
	}
}