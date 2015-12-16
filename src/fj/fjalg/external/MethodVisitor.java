package fj.fjalg.external;

public interface MethodVisitor<OTerm, OKlass, OCtr, OMethod, OProg> extends fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> {
	default OMethod visitMethod(Method e) {
		return e.accept(this);
	}
}
