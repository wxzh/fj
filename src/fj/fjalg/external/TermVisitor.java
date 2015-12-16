package fj.fjalg.external;

public interface TermVisitor<OTerm, OKlass, OCtr, OMethod, OProg> extends fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> {
	default OTerm visitTerm(Term e) {
		return e.accept(this);
	}
}
