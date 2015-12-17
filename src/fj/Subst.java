package fj;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fj.fjalg.shared.FJAlgQuery;
import fj.fjalg.shared.GFJAlg;
import library.Zero;

interface ISubst<Term> {
	Term subst(Map<String, Term> ctx);
}

public interface Subst<Term, Klass, Ctr, Method, Prog> extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, ISubst<Term>> {
	GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog> alg();

	default Zero<ISubst<Term>> m() {
		throw new RuntimeException();
	}

	default ISubst<Term> Var(String x) {
		return ctx -> ctx.get(x);
	}

	default ISubst<Term> Cast(String c, Term t) {
		return ctx -> alg().Cast(c, visitTerm(t).subst(ctx));
	}

	default ISubst<Term> New(String m, List<Term> ts) {
		return ctx -> alg().New(m, ts.stream().map(t -> visitTerm(t).subst(ctx)).collect(Collectors.toList()));
	}

	default ISubst<Term> FieldAccess(Term t, String f) {
		return ctx -> alg().FieldAccess(visitTerm(t).subst(ctx), f);
	}

	default ISubst<Term> MethodCall(Term c, String m, List<Term> ts) {
		return ctx -> alg().MethodCall(visitTerm(c).subst(ctx), m, ts.stream().map(t -> visitTerm(t).subst(ctx)).collect(Collectors.toList()));
	}
}