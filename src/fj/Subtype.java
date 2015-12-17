package fj;

import java.util.List;
import java.util.Map;

import fj.fjalg.external.FJAlgMatcher;
import fj.fjalg.shared.FJAlgQuery;
import library.Tuple2;
import library.Zero;

interface ISubtype<Klass> {
	boolean subtype(Klass klass);
}

/**
 * Subtyping: C <: D
 * =================
 *
 * C <: C (S-Refl)
 *
 * C <: D  D <: E
 * --------------  (S-Trans)
 *    C <: E
 *
 * CT(C) = class C extends D {...}
 * ------------------------------- (S-Subclass)
 *            C <: D
 *
 * C <: Object (S-Top)
 */
public interface Subtype<Term, Klass, Ctr, Method, Prog> extends FJAlgQuery<Term, Klass, Ctr, Method, Prog, ISubtype<Klass>> {
	FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Boolean> matcher();
	Map<String, Klass> classTable();

	default Zero<ISubtype<Klass>> m() {
		throw new RuntimeException();
	}

	default ISubtype<Klass> Class(String self, String parent, List<Tuple2<String, String>> fields, Ctr ctr, List<Method> methods) {
		return C -> matcher()
				.Class(self2 -> parent2 -> fields2 -> ctr2 -> methods2
						-> self.equals(self2) // S-Refl
						|| parent.equals(self2) // S-Subclass
						|| visitKlass(classTable().get(parent)).subtype(classTable().get(self2))) // S-Trans
				.Object(() -> true) // S-Top
				.otherwise(() -> m().empty().subtype(C))
				.visitKlass(C);
	}

	default ISubtype<Klass> Object() {
		return C -> matcher()
				.Object(() -> true)  // S-Refl
				.otherwise(() -> false) // S-Top
				.visitKlass(C);
	}
}