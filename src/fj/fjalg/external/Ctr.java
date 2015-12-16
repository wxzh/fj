package fj.fjalg.external;

public interface Ctr {
	<OTerm, OKlass, OCtr, OMethod, OProg> OCtr accept(fj.fjalg.shared.GFJAlg<Term, Klass, Ctr, Method, Prog, OTerm, OKlass, OCtr, OMethod, OProg> v);
}
