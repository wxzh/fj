package fj.fjalg.external;

public interface ProgVisitor<OTerm, OKlass, OCtr, OMethod, OProg> extends fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> {
	default OProg visitProg(Prog e) {
		return e.accept(this);
	}
}
