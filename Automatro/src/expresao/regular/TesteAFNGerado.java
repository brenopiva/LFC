package expresao.regular;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import afn.AutomatroFN;
import afn.NaoEstadoException;
import alfabeto.Alfabeto;
import alfabeto.SimbNaoAlfabetoException;

public class TesteAFNGerado {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws SimbNaoAlfabetoException, NaoEstadoException {
		char[] alfabeto = {'a','b'};
		Alfabeto alf = new Alfabeto(alfabeto);
		
		CriarAFNExcutar c = new CriarAFNExcutar(alf);
		//a
		AutomatroFN a = c.criarAFN('a', alf);
		a.printAFN();
		
		//b
		AutomatroFN b = c.criarAFN('b', alf);
		b.printAFN();
		
		//E
		AutomatroFN e = c.criarAFN('E', alf);
		e.printAFN();
		
		//V
		AutomatroFN v = c.criarAFN('V', alf);
		v.printAFN();
	}

}
