package fj.fjalg.external;

public interface CtrVisitor<OTerm, OKlass, OCtr, OMethod, OProg> extends fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> {
	default OCtr visitCtr(Ctr e) {
		return e.accept(this);
	}
}
