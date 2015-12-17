package fj;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fj.fjalg.shared.FJAlgQuery;
import library.Tuple2;
import library.Zero;

/**
 * Fields lookup: fields(C) = [C f]
 * ================================
 *
 * fields(Object) = []
 *
 * CT(C) = class C extends D {[C f;] K [M]}
 *          fields(D) = [D g]
 * ----------------------------------------
 *       fields(C) = [D g] ++ [C f]
 */
public interface Fields<Term, Klass, Ctr, Method, Prog>
		extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, List<Tuple2<String, String>>> {
	Map<String, Klass> classTable();
	default Zero<List<Tuple2<String, String>>> m() {
		throw new RuntimeException();
	}

	default List<Tuple2<String, String>> Class(String name, String parent, List<Tuple2<String, String>> fields, Ctr ctr,
			List<Method> methods) {

		return Stream.concat(fields.stream(),
				visitKlass(classTable().get(parent)).stream()).collect(Collectors.toList());
	}

	default List<Tuple2<String, String>> Object() {
		return Collections.emptyList();
	}
}