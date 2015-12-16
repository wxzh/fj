package fj.fjalg.internal;

public interface Term {
	<OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
