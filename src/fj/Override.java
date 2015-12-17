package fj;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Valid method overriding: override(m, D, [C] -> C0)
 * ==================================================
 *
 * mtype(m, D) = [D] -> D0 implies [C] = [D] and C0 = D0
 * -----------------------------------------------------
 *            override(m, D, [C] -> C0)
 *
 */
public interface Override<Term, Klass, Ctr, Method, Prog> {
	Mtype<Term, Klass, Ctr, Method, Prog> mtype();

	default boolean override(String m, Klass D, List<Klass> Cs, Klass C0) {
		MethodType md = mtype().visitKlass(D).mtype(m);
		if (md == null) return true;
		List<String> Ds = md.tyParams;
		String D0 = md.tyReturn;
		return Cs.size() == Ds.size() && IntStream.range(0, Cs.size()).allMatch(i -> Cs.get(i).equals(Ds.get(i))) &&
				C0.equals(D0);
	}
}