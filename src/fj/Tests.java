package fj;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import fj.fjalg.external.Ctr;
import fj.fjalg.external.CtrVisitor;
import fj.fjalg.external.FJAlgFactory;
import fj.fjalg.external.Klass;
import fj.fjalg.external.KlassVisitor;
import fj.fjalg.external.Method;
import fj.fjalg.external.MethodVisitor;
import fj.fjalg.external.Prog;
import fj.fjalg.external.ProgVisitor;
import fj.fjalg.external.Term;
import fj.fjalg.external.TermVisitor;
import library.Tuple2;

public class Tests {
	FJAlgFactory alg = new FJAlgFactory();
	Klass A = klass("A");
	Klass B = klass("B");
	List<Tuple2<String, String>> fstsnd = Arrays.asList(new Tuple2<>("Object", "fst"), new Tuple2<>("Object", "snd"));
	Klass Pair = alg.Class("Pair", "Object", fstsnd,
			alg.Constructor("Pair", fstsnd, Collections.emptyList(),
					Arrays.asList(new Tuple2<>("fst", "fst"), new Tuple2<>("snd", "snd"))),
			Collections.singletonList(alg.Method("Pair", "setfst",
					Collections.singletonList(new Tuple2<>("Object", "newfst")),
					alg.New("Pair", Arrays.asList(alg.Var("newfst"), alg.FieldAccess(alg.Var("this"), "snd"))))));
	Term t = alg
			.MethodCall(
					alg.New("Pair",
							Arrays.asList(alg.New("A", Collections.emptyList()),
									alg.New("B", Collections.emptyList()))),
					"setfst", Collections.singletonList(alg.New("B", Collections.emptyList())));
	Prog p = alg.Program(Arrays.asList(A, B, Pair), t);

	Klass klass(String name) {
		return alg.Class(name, "Object", emptyList(), alg.Constructor(name, emptyList(), emptyList(), emptyList()),
				emptyList());
	}

	class PrintImpl
			implements Print<Term, Klass, Ctr, Method, Prog>, CtrVisitor<IPrint, IPrint, IPrint, IPrint, IPrint>,
			TermVisitor<IPrint, IPrint, IPrint, IPrint, IPrint>, MethodVisitor<IPrint, IPrint, IPrint, IPrint, IPrint>,
			ProgVisitor<IPrint, IPrint, IPrint, IPrint, IPrint>, KlassVisitor<IPrint, IPrint, IPrint, IPrint, IPrint> {
	}

	PrintImpl print = new PrintImpl();

	@Test
	public void test() throws IOException {
		assertEquals(String.join("\n", Files.readAllLines(Paths.get("./src/Pair.java"))),
				p.accept(print).print(""));
	}

}
