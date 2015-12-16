package fj;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fj.fjalg.external.FJAlgMatcher;
import fj.fjalg.shared.FJAlgQuery;
import library.Tuple2;

interface IMtype {
	MethodType mtype(String m);
}

class MethodType {
	String tyReturn;
	List<String> tyParams;
	public MethodType(String tyReturn, List<String> tyParams) {
		this.tyReturn = tyReturn;
		this.tyParams = tyParams;
	}
}

/**
 * Method type lookup: mtype(m, C) = [C] -> C
 * ==========================================
 * CT(C) = class C extends D {[C f;] K [M]}
 *   B m([B x]) {return t;} \in [M]
 * ----------------------------------------
 *     mtype(m, C) = [B] -> B
 *
 * CT(C) = class C extends D {[C f;] K [M]}
 *        m is not defined in [M]
 * ----------------------------------------
 *     mtype(m, C) = mtype(m, D)
 */
public interface Mtype<Term, Klass, Ctr, Method, Prog> extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, IMtype> {
	Map<String, Klass> classTable();
	FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Boolean> mMatcher();
	FJAlgMatcher<Term, Klass, Ctr, Method, Prog, MethodType> mExtractor();

	default IMtype Class(String self, String parent, List<Tuple2<String, String>> fields, Ctr ctr, List<Method> methods) {
		return method -> methods.stream()
				.filter(m -> mMatcher()
						.Method(c -> name -> params -> body -> name.equals(method))
						.otherwise(() -> false)
						.visitMethod(m))
				.findFirst().map(m -> mExtractor()
						.Method(c -> name -> params -> body -> new MethodType(c, params.
								stream().map(pr -> pr._1).collect(Collectors.toList())))
						.otherwise(() -> null)
						.visitMethod(m))
				.orElse(visitKlass(classTable().get(parent)).mtype(method));
	}
}