package fj;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import fj.fjalg.external.Ctr;
import fj.fjalg.external.FJAlgFactory;
import fj.fjalg.external.FJAlgMatcher;
import fj.fjalg.external.FJAlgMatcherImpl;
import fj.fjalg.external.FJAlgVisitor;
import fj.fjalg.external.Klass;
import fj.fjalg.external.Method;
import fj.fjalg.external.Prog;
import fj.fjalg.external.Term;
import fj.fjalg.shared.GFJAlg;
import library.Tuple2;

public class Tests {
	FJAlgFactory alg = new FJAlgFactory();
	Klass Object = alg.Object();
	Klass A = klass("A", "Object");
	Klass B = klass("B", "Object");
	Klass C = klass("C", "A");
	List<Tuple2<String, String>> fstsnd = asList(new Tuple2<>("Object", "fst"), new Tuple2<>("Object", "snd"));
	Term body = alg.New("Pair", asList(alg.Var("newfst"), alg.FieldAccess(alg.Var("this"), "snd")));
	Method setfst = alg.Method("Pair", "setfst", asList(new Tuple2<>("Object", "newfst")), body);
	Klass Pair = alg.Class("Pair", "Object", fstsnd, alg.Constructor("Pair", fstsnd, asList(), asList("fst", "snd")),
			asList(setfst));

	Term newA = alg.New("A", asList());
	Term newB = alg.New("B", asList());
	Term newPair = alg.New("Pair", asList(newA, newB));
	Term callSetfst = alg.MethodCall(newPair, "setfst", asList(newB));
	Prog p = alg.Program(asList(A, B, Pair), callSetfst);
	Map<String, Klass> CT = initCT();

	Klass klass(String name, String superName) {
		return alg.Class(name, superName, asList(), alg.Constructor(name, asList(), asList(), asList()), asList());
	}

	class PrintImpl
			implements Print<Term, Klass, Ctr, Method, Prog>, FJAlgVisitor<IPrint, IPrint, IPrint, IPrint, IPrint> {
	}

	class SubtypeImpl implements Subtype<Term, Klass, Ctr, Method, Prog>,
			FJAlgVisitor<ISubtype<Klass>, ISubtype<Klass>, ISubtype<Klass>, ISubtype<Klass>, ISubtype<Klass>> {
		public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Boolean> matcher() {
			return new FJAlgMatcherImpl<>();
		}

		public Map<String, Klass> classTable() {
			return CT;
		}
	}

	class IsValImpl implements IsVal<Term, Klass, Ctr, Method, Prog>,
			FJAlgVisitor<Boolean, Boolean, Boolean, Boolean, Boolean> {
	}

	class FieldsImpl implements Fields<Term, Klass, Ctr, Method, Prog>,
			FJAlgVisitor<List<Tuple2<String, String>>, List<Tuple2<String, String>>, List<Tuple2<String, String>>, List<Tuple2<String, String>>, List<Tuple2<String, String>>> {
		public Map<String, Klass> classTable() {
			return CT;
		}
	}

	class MtypeImpl
			implements Mtype<Term, Klass, Ctr, Method, Prog>, FJAlgVisitor<IMtype, IMtype, IMtype, IMtype, IMtype> {
		public Map<String, Klass> classTable() {
			return CT;
		}

		public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, MethodType> mExtractor() {
			return new FJAlgMatcherImpl<>();
		}

		public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Boolean> mMatcher() {
			return new FJAlgMatcherImpl<>();
		}
	}

	class MbodyImpl implements Mbody<Term, Klass, Ctr, Method, Prog>,
			FJAlgVisitor<IMbody<Term>, IMbody<Term>, IMbody<Term>, IMbody<Term>, IMbody<Term>> {

		public Map<String, Klass> classTable() {
			return CT;
		}

		public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, MethodBody<Term>> mExtractor() {
			return new FJAlgMatcherImpl<>();
		}

		public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Boolean> mMatcher() {
			return new FJAlgMatcherImpl<>();
		}
	}

	class OverrideImpl implements Override<Term, Klass, Ctr, Method, Prog> {
		public Mtype<Term, Klass, Ctr, Method, Prog> mtype() {
			return mtype;
		}
	}

	class TypeofImpl implements Typeof<Term, Klass, Ctr, Method, Prog>,
			FJAlgVisitor<ITypeof, Void, TCheckCtr, TCheckMethod, String> {
		public Fields<Term, Klass, Ctr, Method, Prog> fields() {
			return fields;
		}

		public Subtype<Term, Klass, Ctr, Method, Prog> subtype() {
			return subtype;
		}

		public Override<Term, Klass, Ctr, Method, Prog> override() {
			return override;
		}

		public Mtype<Term, Klass, Ctr, Method, Prog> mtype() {
			return mtype;
		}

		public Map<String, Klass> classTable() {
			return CT;
		}

		public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Klass> matcher() {
			return new FJAlgMatcherImpl<>();
		}
	}

	class SubstImpl implements Subst<Term, Klass, Ctr, Method, Prog>,
			FJAlgVisitor<ISubst<Term>, ISubst<Term>, ISubst<Term>, ISubst<Term>, ISubst<Term>> {
		public GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog> alg() {
			return alg;
		}
	}

	class Eval1Impl implements Eval1<Term, Klass, Ctr, Method, Prog>, FJAlgVisitor<Term, Term, Term, Term, Term> {
		public GFJAlg<Term, Klass, Ctr, Method, Prog, Term, Klass, Ctr, Method, Prog> alg() {
			return alg;
		}

		public Fields<Term, Klass, Ctr, Method, Prog> fields() {
			return fields;
		}

		public FJAlgMatcher<Term, Klass, Ctr, Method, Prog, Term> matcher() {
			return new FJAlgMatcherImpl<>();
		}

		public IsVal<Term, Klass, Ctr, Method, Prog> isVal() {
			return isVal;
		}

		public Mbody<Term, Klass, Ctr, Method, Prog> mbody() {
			return mbody;
		}

		public Subtype<Term, Klass, Ctr, Method, Prog> subtype() {
			return subtype;
		}

		public Subst<Term, Klass, Ctr, Method, Prog> subst() {
			return subst;
		}

		public Map<String, Klass> classTable() {
			return CT;
		}
	}

	PrintImpl print = new PrintImpl();
	TypeofImpl typeof = new TypeofImpl();
	IsValImpl isVal = new IsValImpl();
	MbodyImpl mbody = new MbodyImpl();
	MtypeImpl mtype = new MtypeImpl();
	FieldsImpl fields = new FieldsImpl();
	SubtypeImpl subtype = new SubtypeImpl();
	OverrideImpl override = new OverrideImpl();
	Eval1Impl eval1 = new Eval1Impl();
	SubstImpl subst = new SubstImpl();

	public Map<String, Klass> initCT() {
		Map<String, Klass> classTable = new HashMap<>();
		classTable.put("Object", alg.Object());
		classTable.put("A", A);
		classTable.put("B", B);
		classTable.put("C", C);
		classTable.put("Pair", Pair);
		return classTable;
	}

	String read(String name) {
		try {
			return String.join("\n", Files.readAllLines(Paths.get("./src/" + name + ".fj")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}

	@Test
	public void testPrint() {
		assertEquals(String.join("\n", read("A"), read("B"), read("Pair")), p.accept(print).print(""));
	}

	@Test
	public void testIsVal() {
		assertTrue(newA.accept(isVal));
		assertTrue(newPair.accept(isVal));
		assertFalse(callSetfst.accept(isVal));
		assertFalse(alg.FieldAccess(newPair, "fst").accept(isVal));
		assertFalse(alg.New("Pair", asList(newA, alg.FieldAccess(newPair, "snd"))).accept(isVal));
	}

	@Test
	public void testCT() {
		assertEquals(read("B"), CT.get("B").accept(print).print(""));
	}

	@Test
	public void testMbody() {
		MethodBody<Term> mb = Pair.accept(mbody).mbody("setfst");
		assertEquals("[newfst]", mb.params.toString());
		assertEquals(body.accept(print).print(""), mb.body.accept(print).print(""));
	}

	@Test
	public void testMtype() {
		MethodType mt = Pair.accept(mtype).mtype("setfst");
		assertEquals("Pair", mt.tyReturn);
		assertEquals("[Object]", mt.tyParams.toString());
		assertNull(Object.accept(mtype).mtype("unknown"));
		assertNull(Pair.accept(mtype).mtype("unknown"));
	}

	@Test
	public void testFields() {
		assertEquals("[]", alg.Object().accept(fields).toString());
		assertEquals("[]", A.accept(fields).toString());
		assertEquals("[(Object, fst), (Object, snd)]", Pair.accept(fields).toString());
	}

	@Test
	public void testSubtype() {
		assertFalse(Object.accept(subtype).subtype(A));
		// Refl
		assertTrue(Object.accept(subtype).subtype(Object));
		assertTrue(A.accept(subtype).subtype(A));

		// Trans
		assertTrue(A.accept(subtype).subtype(Object));
		assertTrue(C.accept(subtype).subtype(A));
		assertTrue(C.accept(subtype).subtype(Object));

		// Subclass
		assertTrue(C.accept(subtype).subtype(A));
		assertFalse(C.accept(subtype).subtype(B));
	}

	@Test
	public void testOverride() {
		assertEquals("A", newA.accept(typeof).typeof(emptyMap()));
	}

	@Test
	public void testTypeof() {
		assertEquals("A", newA.accept(typeof).typeof(emptyMap()));
		// upcast
		assertEquals("Object", alg.Cast("Object", newA).accept(typeof).typeof(emptyMap()));
		// downcast
		assertEquals("C", alg.Cast("C", newA).accept(typeof).typeof(emptyMap()));
		assertEquals("B", alg.Cast("B", newA).accept(typeof).typeof(emptyMap())); // stupid
																					// warning
		assertEquals("Object", alg.FieldAccess(newPair, "fst").accept(typeof).typeof(emptyMap()));
		assertEquals("A", alg.Var("x").accept(typeof).typeof(Collections.singletonMap("x", "A")));
		assertEquals("Pair", callSetfst.accept(typeof).typeof(emptyMap()));
		assertEquals("Pair", p.accept(typeof));
	}

	@Test
	public void testEval1() {
		Term t = callSetfst;
		assertEquals("new Pair(new A(), new B()).setfst(new B())", t.accept(print).print(""));
		t = t.accept(eval1);
		assertEquals("new Pair(new B(), new Pair(new A(), new B()).snd)", t.accept(print).print(""));
		t = t.accept(eval1);
		assertEquals("new Pair(new B(), new B())", t.accept(eval1).accept(print).print(""));
	}
}