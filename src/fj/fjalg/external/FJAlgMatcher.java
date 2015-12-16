package fj.fjalg.external;

public interface FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> extends FJAlgMapper<ITerm, IKlass, ICtr, IMethod, IProg, O> {
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> Cast(java.util.function.Function<java.lang.String, java.util.function.Function<ITerm, O>> Cast);
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> New(java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<ITerm>, O>> New);
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> Program(java.util.function.Function<java.util.List<IKlass>, java.util.function.Function<ITerm, O>> Program);
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> Var(java.util.function.Function<java.lang.String, O> Var);
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> Constructor(java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<java.util.List<java.lang.String>, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, O>>>> Constructor);
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> Class(java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<ICtr, java.util.function.Function<java.util.List<IMethod>, O>>>>> Class);
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> Object(java.util.function.Supplier<O> Object);
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> Method(java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<ITerm, O>>>> Method);
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> FieldAccess(java.util.function.Function<ITerm, java.util.function.Function<java.lang.String, O>> FieldAccess);
	FJAlgMatcher<ITerm, IKlass, ICtr, IMethod, IProg, O> MethodCall(java.util.function.Function<ITerm, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<ITerm>, O>>> MethodCall);
	fj.fjalg.shared.GFJAlg<ITerm, IKlass, ICtr, IMethod, IProg, O, O, O, O, O> otherwise(java.util.function.Supplier<O> Otherwise);
}
