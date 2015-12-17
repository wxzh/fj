package fj;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fj.fjalg.shared.GFJAlg;
import library.Tuple2;

interface IPrint {
	String print(String tabs);
}

public interface Print<Term, Klass, Ctr, Method, Prog>
		extends GFJAlg<Term, Klass, Ctr, Method, Prog, IPrint, IPrint, IPrint, IPrint, IPrint> {
	default IPrint Program(List<Klass> classes, Term t) {
		return tabs -> classes.stream().map(c -> visitKlass(c).print(tabs)).collect(Collectors.joining("\n")) + "\n"
				+ visitTerm(t).print(tabs);
	}

	default IPrint Var(String x) {
		return tabs -> tabs + x;
	}

	default IPrint FieldAccess(Term t, String f) {
		return tabs -> tabs + visitTerm(t).print("") + "." + f;
	}

	default IPrint MethodCall(Term t, String name, List<Term> ts) {
		return tabs -> tabs + visitTerm(t).print("") + "." + name + printTerms(ts);
	}

	default IPrint New(String ty, List<Term> ts) {
		return tabs -> tabs + "new " + ty + printTerms(ts);
	}

	default IPrint Cast(String ty, Term t) {
		return tabs -> tabs + "(" + ty + ") " + visitTerm(t).print("");
	}

	default IPrint Class(String name, String parent, List<Tuple2<String, String>> fields, Ctr ctr,
			List<Method> methods) {
		return tabs -> {
			String newTabs = tabs + "\t";
			String printFields = fields.stream().map(pr -> newTabs + pr._1 + " " + pr._2 + ";")
					.collect(Collectors.joining("\n"));
			String printMethods = methods.stream().map(m -> visitMethod(m).print(newTabs))
					.collect(Collectors.joining("\n"));
			return Stream.of(tabs + "class " + name + " extends " + parent + " {", printFields,
					visitCtr(ctr).print(newTabs), printMethods, "}").filter(s -> s.length() != 0).collect(Collectors.joining("\n"));

		};
	}

	default IPrint Object() {
		return tabs -> "class Object {\n" + "\tsuper();\n" + "}";
	}

	default IPrint Constructor(String name, List<Tuple2<String, String>> params, List<String> gs,
			List<String> fs) {
		return tabs -> {
			String newTabs = tabs + "\t";
			return Stream.of(tabs + name + printParams(params) + " {",
					newTabs + "super(" + String.join(", ", gs) + ");", fs.stream()
							.map(f -> newTabs + "this." + f + " = " + f + ";").collect(Collectors.joining("\n")),
					tabs + "}").filter(s -> s.length() != 0).collect(Collectors.joining("\n"));
		};
	}

	default IPrint Method(String returnTy, String name, List<Tuple2<String, String>> params, Term body) {
		return tabs -> String.join("\n", tabs + returnTy + " " + name + printParams(params) + " {", tabs + "\treturn " + visitTerm(body).print("") + ";",  tabs + "}");
	}

	// helper
	default String printTerms(List<Term> ts) {
		return ts.stream().map(t -> visitTerm(t).print("")).collect(Collectors.joining(", ", "(", ")"));
	}

	default String printParams(List<Tuple2<String, String>> params) {
		return params.stream().map(pr -> pr._1 + " " + pr._2).collect(Collectors.joining(", ", "(", ")"));
	}
}