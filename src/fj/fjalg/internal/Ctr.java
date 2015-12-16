package fj.fjalg.internal;

public interface Ctr {
	<OTerm, OKlass, OCtr, OMethod, OProg> OCtr accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
