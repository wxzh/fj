package fj.fjalg.external;

public interface Method {
	<OTerm, OKlass, OCtr, OMethod, OProg> OMethod accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
