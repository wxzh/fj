package fj.fjalg.internal;

public interface Method {
	<OTerm, OKlass, OCtr, OMethod, OProg> OMethod accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
