package fj.fjalg.external;

public interface Prog {
	<OTerm, OKlass, OCtr, OMethod, OProg> OProg accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
