package fj.fjalg.external;

public class FJAlgFactory implements fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog>, TermVisitor<Term, Klass, Ctr, Method, Prog>, KlassVisitor<Term, Klass, Ctr, Method, Prog>, CtrVisitor<Term, Klass, Ctr, Method, Prog>, MethodVisitor<Term, Klass, Ctr, Method, Prog>, ProgVisitor<Term, Klass, Ctr, Method, Prog> {
	public Term Cast(java.lang.String p1, Term p2) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Cast(p1, p2);
			}
		};
	}

	public Term New(java.lang.String p1, java.util.List<Term> p2) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.New(p1, p2);
			}
		};
	}

	public Prog Program(java.util.List<Klass> p1, Term p2) {
		return new Prog() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OProg accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Program(p1, p2);
			}
		};
	}

	public Term Var(java.lang.String p1) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Var(p1);
			}
		};
	}

	public Ctr Constructor(java.lang.String p1, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p2, java.util.List<java.lang.String> p3, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p4) {
		return new Ctr() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OCtr accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Constructor(p1, p2, p3, p4);
			}
		};
	}

	public Klass Class(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, Ctr p4, java.util.List<Method> p5) {
		return new Klass() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OKlass accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Class(p1, p2, p3, p4, p5);
			}
		};
	}

	public Klass Object() {
		return new Klass() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OKlass accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Object();
			}
		};
	}

	public Method Method(java.lang.String p1, java.lang.String p2, java.util.List<library.Tuple2<java.lang.String, java.lang.String>> p3, Term p4) {
		return new Method() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OMethod accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.Method(p1, p2, p3, p4);
			}
		};
	}

	public Term FieldAccess(Term p1, java.lang.String p2) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.FieldAccess(p1, p2);
			}
		};
	}

	public Term MethodCall(Term p1, java.lang.String p2, java.util.List<Term> p3) {
		return new Term() {
			public <OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v) {
				return v.MethodCall(p1, p2, p3);
			}
		};
	}
}