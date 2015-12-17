package fj;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fj.fjalg.external.FJAlgMatcher;
import fj.fjalg.shared.FJAlgQuery;
import library.Tuple2;
import library.Zero;

interface IMbody<Term> {
	MethodBody<Term> mbody(String m);
}

class MethodBody<Term> {
	List<String> params;
	Term body;
	public MethodBody(List<String> params, Term body) {
		this.params = params;
		this.body = body;
	}
}

/**
 * Method body lookup: mbody(m, C) = ([x], t)
 * ==========================================
 *
 * CT(C) = class C extends D {[C f;] K [M]}
 *   B m([B x]) {return t;} \in [M]
 * ----------------------------------------
 *     mbody(m, C) = ([x], t)
 *
 * CT(C) = class C extends D {[C f;] K [M]}
 *        m is not defined in [M]
 * ----------------------------------------
 *     mbody(m, C) = mbody(m, D)
 */
public interface Mbody<Term, Klass, Ctr, Method, Prog> extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, IMbody<Term>> {
	Map<String, Klass> classTable();
	FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Boolean> mMatcher();
	FJAlgMatcher<Term, Klass, Ctr, Method, Prog, MethodBody<Term>> mExtractor();

	default Zero<IMbody<Term>> m() {
		throw new RuntimeException();
	}

	default IMbody<Term> Class(String self, String parent, List<Tuple2<String, String>> fields, Ctr ctr, List<Method> methods) {
		return method -> methods.stream()
				.filter(m -> mMatcher()
						.Method(returnTy -> name -> params -> body -> name.equals(method))
						.otherwise(() -> false)
						.visitMethod(m))
				.findFirst().map(m -> mExtractor()
						.Method(tyReturn -> name -> params -> body -> new MethodBody<>(params.stream().map(pr -> pr._2).collect(Collectors.toList()), body))
						.otherwise(() -> null)
						.visitMethod(m))
				.orElseGet(() -> visitKlass(classTable().get(parent)).mbody(method));
	}
}