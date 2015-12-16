package fj;

import java.util.List;

import library.Tuple2;

public interface FJAlg<Term, Klass, Ctr, Method, Prog> {
	Prog Program(List<Klass> classes, Term t);

	// x
	Term Var(String x);

	// t.f
	Term FieldAccess(Term t, String f);

	// t.m([t])
	Term MethodCall(Term t, String name, List<Term> ts);

	// new C([t])
	Term New(String ty, List<Term> ts);

	// (C) t
	Term Cast(String ty, Term t);

	// class C extends C {[C f;] K [M]}
	Klass Class(String name, String parent, List<Tuple2<String, String>> fields, Ctr ctr, List<Method> methods);
	Klass Object();

	// C([C f]) {super([f]); this.[f] = [f];}
	Ctr Constructor(String name, List<Tuple2<String, String>> params, List<String> xs, List<Tuple2<String, String>> assignments);

	// C m([C x]) {return t;}
	Method Method(String returnTy, String name, List<Tuple2<String, String>> params, Term body);
}