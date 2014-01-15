package alfabeto;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TesteAlfabeto {

	@Test
	public void test() throws SimbNaoAlfabetoException {
			char[] alf = {'d','c','a'};
			char[] alfabeto = {'E','a','c','d'};
			Alfabeto a = criarAlfabeto(alf);
			
			print(a);
			assertEquals(4, a.getTamAlf());
			assertArrayEquals(alfabeto, a.getSimbolos());
			assertEquals('a', a.getSimbolo(1));
			assertEquals('E', a.getSimbolo(0));
			assertEquals(3, a.getChave('d'));
			assertEquals(1, a.getChave('a'));
	}
	
	private void print(Alfabeto a) {
		for (char c : a.getSimbolos()) {
			System.out.print(c+" ");
		}
	}
	private Alfabeto criarAlfabeto(char[] a) {
		return new Alfabeto(a);
	}

}
