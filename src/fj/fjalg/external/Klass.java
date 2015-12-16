package fj.fjalg.external;

public interface Klass {
	<OTerm, OKlass, OCtr, OMethod, OProg> OKlass accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
