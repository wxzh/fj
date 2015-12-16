package fj;

import java.util.List;

import fj.fjalg.shared.FJAlgQuery;

public interface IsVal<Term, Klass, Ctr, Method, Prog> extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, Boolean> {
	default Boolean New(String c, List<Term> ts) {
		return ts.stream().allMatch(this::visitTerm);
	}
}