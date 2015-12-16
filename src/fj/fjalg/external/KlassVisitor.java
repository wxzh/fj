package fj.fjalg.external;

public interface KlassVisitor<OTerm, OKlass, OCtr, OMethod, OProg> extends fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> {
	default OKlass visitKlass(Klass e) {
		return e.accept(this);
	}
}
