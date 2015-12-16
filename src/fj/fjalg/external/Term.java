package fj.fjalg.external;

public interface Term {
	<OTerm, OKlass, OCtr, OMethod, OProg> OTerm accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
