package fj.fjalg.external;

public interface FJAlgMapper<ITerm, IKlass, ICtr, IMethod, IProg, O> {
	java.util.function.Function<java.lang.String, java.util.function.Function<ITerm, O>> CastMapper();
	java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<ITerm>, O>> NewMapper();
	java.util.function.Function<java.util.List<IKlass>, java.util.function.Function<ITerm, O>> ProgramMapper();
	java.util.function.Function<java.lang.String, O> VarMapper();
	java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<java.util.List<java.lang.String>, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, O>>>> ConstructorMapper();
	java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<ICtr, java.util.function.Function<java.util.List<IMethod>, O>>>>> ClassMapper();
	java.util.function.Supplier<O> ObjectMapper();
	java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<ITerm, O>>>> MethodMapper();
	java.util.function.Function<ITerm, java.util.function.Function<java.lang.String, O>> FieldAccessMapper();
	java.util.function.Function<ITerm, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<ITerm>, O>>> MethodCallMapper();}