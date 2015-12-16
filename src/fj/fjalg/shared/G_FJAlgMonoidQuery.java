package fj.fjalg.shared;

public interface G_FJAlgMonoidQuery<ITerm, IKlass, ICtr, IMethod, IProg, OTerm, OKlass, OCtr, OMethod, OProg> extends fj.fjalg.shared.GFJAlg<ITerm, IKlass, ICtr, IMethod, IProg, OTerm, OKlass, OCtr, OMethod, OProg> {
	library.Monoid<OCtr> mCtr();
	library.Monoid<OKlass> mKlass();
	library.Monoid<OTerm> mTerm();
	library.Monoid<OMethod> mMethod();
	library.Monoid<OProg> mProg();

	default OTerm Cast(java.lang.String p1, ITerm p2) {
		return visitTerm(p2);
	}

	default OTerm New(java.lang.String p1, java.util.List<ITerm> p2) {
		return p2.stream().map(_p2 -> visitTerm(_p2)).reduce(mTerm().empty(), mTerm()::join);
	}

	default OProg Program(java.util.List<IKlass> p1, ITerm p2) {
		return mProg().empty();
	}

	default OTerm Var(java.lang.String p1) {
		return mTerm().empty();
	}

	default OCtr Constructor(java.lang.String p1, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p2, java.util.List<java.lang.String> p3, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p4) {
		return mCtr().empty();
	}

	default OKlass Class(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, ICtr p4, java.util.List<IMethod> p5) {
		return mKlass().empty();
	}

	default OKlass Object() {
		return mKlass().empty();
	}

	default OMethod Method(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, ITerm p4) {
		return mMethod().empty();
	}

	default OTerm FieldAccess(ITerm p1, java.lang.String p2) {
		return visitTerm(p1);
	}

	default OTerm MethodCall(ITerm p1, java.lang.String p2, java.util.List<ITerm> p3) {
		return java.util.stream.Stream.of(visitTerm(p1), p3.stream().map(_p3 -> visitTerm(_p3)).reduce(mTerm().empty(), mTerm()::join)).reduce(mTerm().empty(), mTerm()::join);
	}
}
