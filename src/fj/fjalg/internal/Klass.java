package fj.fjalg.internal;

public interface Klass {
	<OTerm, OKlass, OCtr, OMethod, OProg> OKlass accept(fj.fjalg.shared.GFJAlg<OTerm, OKlass, OCtr, OMethod, OProg, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
