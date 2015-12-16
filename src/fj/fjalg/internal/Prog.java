package fj.fjalg.internal;

public interface Prog {
	<OTerm, OKlass, OCtr, OMethod, OProg> OProg accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
