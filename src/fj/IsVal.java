package fj;

import java.util.List;

import fj.fjalg.shared.FJAlgQuery;
import library.Zero;

public interface IsVal<Term, Klass, Ctr, Method, Prog> extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, Boolean> {
	default Zero<Boolean> m() {
		return () -> false;
	}

	default Boolean New(String c, List<Term> ts) {
		return ts.stream().allMatch(this::visitTerm);
	}
}