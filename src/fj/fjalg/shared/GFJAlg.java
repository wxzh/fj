package fj.fjalg.shared;

public interface GFJAlg<ITerm, IKlass, ICtr, IMethod, IProg, OTerm, OKlass, OCtr, OMethod, OProg> extends fj.fjalg.shared.VisitCtr<ICtr, OCtr>, fj.fjalg.shared.VisitKlass<IKlass, OKlass>, fj.fjalg.shared.VisitTerm<ITerm, OTerm>, fj.fjalg.shared.VisitMethod<IMethod, OMethod>, fj.fjalg.shared.VisitProg<IProg, OProg> {
	OTerm Cast(java.lang.String p1, ITerm p2);
	OTerm New(java.lang.String p1, java.util.List<ITerm> p2);
	OProg Program(java.util.List<IKlass> p1, ITerm p2);
	OTerm Var(java.lang.String p1);
	OCtr Constructor(java.lang.String p1, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p2, java.util.List<java.lang.String> p3, java.util.List<java.lang.String> p4);
	OKlass Class(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, ICtr p4, java.util.List<IMethod> p5);
	OKlass Object();
	OMethod Method(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, ITerm p4);
	OTerm FieldAccess(ITerm p1, java.lang.String p2);
	OTerm MethodCall(ITerm p1, java.lang.String p2, java.util.List<ITerm> p3);
}