package fj.fjalg.internal;

public class FJAlgFactory implements fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog>, TermVisitor<Term>, KlassVisitor<Klass>, CtrVisitor<Ctr>, MethodVisitor<Method>, ProgVisitor<Prog> {
	public Term Cast(java.lang.String p1, Term p2) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Cast(p1, p2.accept(v));
			}
		};
	}

	public Term New(java.lang.String p1, java.util.List<Term> p2) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.New(p1, p2.stream().map(_p2 -> _p2.accept(v)).collect(java.util.stream.Collectors.toList()));
			}
		};
	}

	public Prog Program(java.util.List<Klass> p1, Term p2) {
		return new Prog() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OProg accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Program(p1.stream().map(_p1 -> _p1.accept(v)).collect(java.util.stream.Collectors.toList()), p2.accept(v));
			}
		};
	}

	public Term Var(java.lang.String p1) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Var(p1);
			}
		};
	}

	public Ctr Constructor(java.lang.String p1, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p2, java.util.List<java.lang.String> p3, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p4) {
		return new Ctr() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OCtr accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Constructor(p1, p2, p3, p4);
			}
		};
	}

	public Klass Class(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, Ctr p4, java.util.List<Method> p5) {
		return new Klass() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OKlass accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Class(p1, p2, p3, p4.accept(v), p5.stream().map(_p5 -> _p5.accept(v)).collect(java.util.stream.Collectors.toList()));
			}
		};
	}

	public Klass Object() {
		return new Klass() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OKlass accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Object();
			}
		};
	}

	public Method Method(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, Term p4) {
		return new Method() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OMethod accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Method(p1, p2, p3, p4.accept(v));
			}
		};
	}

	public Term FieldAccess(Term p1, java.lang.String p2) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.FieldAccess(p1.accept(v), p2);
			}
		};
	}

	public Term MethodCall(Term p1, java.lang.String p2, java.util.List<Term> p3) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.MethodCall(p1.accept(v), p2, p3.stream().map(_p3 -> _p3.accept(v)).collect(java.util.stream.Collectors.toList()));
			}
		};
	}
}