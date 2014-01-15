package afn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import alfabeto.Alfabeto;
import alfabeto.SimbNaoAlfabetoException;

public class TesteAutomatroFN {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws NaoEstadoException, SimbNaoAlfabetoException, CloneNotSupportedException {
		/**
		 * AFN
		 */
		char[] alfabeto = {'a','b'};
		Alfabeto alf = new Alfabeto(alfabeto);
		
		//a
		AutomatroFN a = new AutomatroFN(2, alf); 
		a.setInicio(0);
		a.getFuncao().setTransicao(0, 'a', 1);
		a.inserirEstadoFinal(1);
		a.printAFN();
		
		//b
		AutomatroFN b = new AutomatroFN(2, alf); 
		b.setInicio(0);
		b.getFuncao().setTransicao(0, 'b', 1);
		b.inserirEstadoFinal(1);
		b.printAFN();
		
		//a U b
		System.out.print("\ta U b\n");
		AutomatroFN aUniaoB = a.uniao(b);
		aUniaoB.printAFN();
		
		//(a U b) U b
		System.out.print("\t(a U b) U b\n");
		AutomatroFN aUniaoBUB = aUniaoB.uniao(b);
		aUniaoBUB.printAFN();
		//b.uniao(aUniaoB).printAFN();
		
		//a.b
		AutomatroFN aConcatB = a.concatenacao(b);
		aConcatB.printAFN();
		
		//a.b.a
		System.out.print("\ta.b.a\n");
		AutomatroFN aCbCa = aConcatB.concatenacao(a);
		aCbCa.printAFN();
		
		//((a U b) U b).a.b
		System.out.print("\t((a U b) U b).a.b\n");
		aUniaoBUB.concatenacao(aConcatB).printAFN();
		
		aCbCa.uniao(aUniaoB).printAFN();
		
		//(a.b)*
		aConcatB.estrela().printAFN();
		
		aConcatB.printAFN();
		
		/**
		 * AFD
		 */
		System.out.print("AFD\n");
//		AFD w = aUniaoB.transfAFNemAFD();
//		w.printAFN();
		
//		Collection<Integer> t = new HashSet<Integer>();
//		Collection<Integer> s = new HashSet<Integer>();
//		Integer[] ab = {4,5,7};
//		s = d.obterFeixoTransVazio(t, ab);
//		System.out.print("OPS "+s.toString());
		AFD d = aUniaoBUB.transfAFNemAFD();
		d.printAFN();

	}
	
	private void Atencao() throws NaoEstadoException, SimbNaoAlfabetoException {
		char[] alfabeto = {'a','b'};
		Alfabeto alf = new Alfabeto(alfabeto);

		/**
		 * função de trasição do AFD
		 */
		//a
		FuncaoTrasicao func = new FuncaoTrasicao(2, alf);
		assertNull(func.transicao(0, 'a'));
		assertNull(func.transicao(1, 'E'));
		func.setTransicao(0, 'a', 1);
		assertEquals(1, (int) func.transicao(0, 'a'));
		
		//b
		FuncaoTrasicao func1 = new FuncaoTrasicao(2, alf);
		func1.setTransicao(0, 'b', 1);
		assertEquals(1, (int) func1.transicao(0, 'b'));
		
		/**
		 * função de trasição AFN
		 */
		FuncaoTrasicaoAfn f = new FuncaoTrasicaoAfn(2, alf);
		f.setTransicao(0, 'a', 1);
		f.setTransicao(0, 'a', 0);
		f.setTransicao(1, 'b', 0);
		f.setTransicao(0, 'E', 0);
		f.setTransicao(0, 'E', 1);
		
		f.printFuncaoTransicao();
	}

}
