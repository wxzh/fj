package fj.fjalg.external;

interface FJAlgApplierWithVisitor<O> extends FJAlgApplier<Term, Klass, Ctr, Method, Prog, O>, TermVisitor<O, O, O, O, O>, KlassVisitor<O, O, O, O, O>, CtrVisitor<O, O, O, O, O>, MethodVisitor<O, O, O, O, O>, ProgVisitor<O, O, O, O, O> {
}

public class FJAlgMatcherImpl<O> implements FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> {
	private java.util.function.Function<java.lang.String, java.util.function.Function<Term, O>> Cast = null;
	private java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<Term>, O>> New = null;
	private java.util.function.Function<java.util.List<Klass>, java.util.function.Function<Term, O>> Program = null;
	private java.util.function.Function<java.lang.String, O> Var = null;
	private java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<java.util.List<java.lang.String>, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, O>>>> Constructor = null;
	private java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<Ctr, java.util.function.Function<java.util.List<Method>, O>>>>> Class = null;
	private java.util.function.Supplier<O> Object = null;
	private java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<Term, O>>>> Method = null;
	private java.util.function.Function<Term, java.util.function.Function<java.lang.String, O>> FieldAccess = null;
	private java.util.function.Function<Term, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<Term>, O>>> MethodCall = null;

	public java.util.function.Function<java.lang.String, java.util.function.Function<Term, O>> CastMapper() {
		return Cast;
	}

	public java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<Term>, O>> NewMapper() {
		return New;
	}

	public java.util.function.Function<java.util.List<Klass>, java.util.function.Function<Term, O>> ProgramMapper() {
		return Program;
	}

	public java.util.function.Function<java.lang.String, O> VarMapper() {
		return Var;
	}

	public java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<java.util.List<java.lang.String>, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, O>>>> ConstructorMapper() {
		return Constructor;
	}

	public java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<Ctr, java.util.function.Function<java.util.List<Method>, O>>>>> ClassMapper() {
		return Class;
	}

	public java.util.function.Supplier<O> ObjectMapper() {
		return Object;
	}

	public java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<Term, O>>>> MethodMapper() {
		return Method;
	}

	public java.util.function.Function<Term, java.util.function.Function<java.lang.String, O>> FieldAccessMapper() {
		return FieldAccess;
	}

	public java.util.function.Function<Term, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<Term>, O>>> MethodCallMapper() {
		return MethodCall;
	}
	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> Cast(java.util.function.Function<java.lang.String, java.util.function.Function<Term, O>> Cast) {
		this.Cast = Cast;
		return this;
	}

	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> New(java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<Term>, O>> New) {
		this.New = New;
		return this;
	}

	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> Program(java.util.function.Function<java.util.List<Klass>, java.util.function.Function<Term, O>> Program) {
		this.Program = Program;
		return this;
	}

	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> Var(java.util.function.Function<java.lang.String, O> Var) {
		this.Var = Var;
		return this;
	}

	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> Constructor(java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<java.util.List<java.lang.String>, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, O>>>> Constructor) {
		this.Constructor = Constructor;
		return this;
	}

	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> Class(java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<Ctr, java.util.function.Function<java.util.List<Method>, O>>>>> Class) {
		this.Class = Class;
		return this;
	}

	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> Object(java.util.function.Supplier<O> Object) {
		this.Object = Object;
		return this;
	}

	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> Method(java.util.function.Function<java.lang.String, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<library.Tuple2<java.lang.String, java.lang.String>>, java.util.function.Function<Term, O>>>> Method) {
		this.Method = Method;
		return this;
	}

	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> FieldAccess(java.util.function.Function<Term, java.util.function.Function<java.lang.String, O>> FieldAccess) {
		this.FieldAccess = FieldAccess;
		return this;
	}

	public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, O> MethodCall(java.util.function.Function<Term, java.util.function.Function<java.lang.String, java.util.function.Function<java.util.List<Term>, O>>> MethodCall) {
		this.MethodCall = MethodCall;
		return this;
	}
	public fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, O, O, O, O, O> otherwise(java.util.function.Supplier<O> otherwise) {
		if (Cast == null) Cast = p1 -> p2 -> otherwise.get();
		if (New == null) New = p1 -> p2 -> otherwise.get();
		if (Program == null) Program = p1 -> p2 -> otherwise.get();
		if (Var == null) Var = p1 -> otherwise.get();
		if (Constructor == null) Constructor = p1 -> p2 -> p3 -> p4 -> otherwise.get();
		if (Class == null) Class = p1 -> p2 -> p3 -> p4 -> p5 -> otherwise.get();
		if (Object == null) Object = () -> otherwise.get();
		if (Method == null) Method = p1 -> p2 -> p3 -> p4 -> otherwise.get();
		if (FieldAccess == null) FieldAccess = p1 -> p2 -> otherwise.get();
		if (MethodCall == null) MethodCall = p1 -> p2 -> p3 -> otherwise.get();

		return new FJAlgApplierWithVisitor<O>() {
			public FJAlgMapper<Term, Klass, Ctr, Method, Prog, O> mapper() {
				return FJAlgMatcherImpl.this;
			}
		};
	}
}
