package fj;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fj.fjalg.shared.FJAlgQuery;
import library.Tuple2;
import library.Zero;

class Field<Klass> {
	Klass ty;
	String name;

	public Field(Klass ty, String name) {
		this.ty = ty;
		this.name = name;
	}
}

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
		extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, List<Field<Klass>>> {
	Map<String, Klass> classTable();

	default Zero<List<Field<Klass>>> m() {
		throw new OperationNotSupported();
	}

	default List<Field<Klass>> Class(String name, String parent, List<Tuple2<String, String>> fields, Ctr ctr,
			List<Method> methods) {

		return Stream.concat(fields.stream().map(pr -> new Field<>(classTable().get(pr._1), pr._2)),
				visitKlass(classTable().get(parent)).stream()).collect(Collectors.toList());
	}

	default List<Field<Klass>> Object() {
		return Collections.emptyList();
	}
}