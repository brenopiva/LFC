package expresao.regular;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import afn.AutomatroFN;
import afn.NaoEstadoException;
import alfabeto.Alfabeto;
import alfabeto.SimbNaoAlfabetoException;

public class CriarAFNExcutar {

	public CriarAFNExcutar() {
		
	}
	
	public AutomatroFN criarAFN(char simbolo, Alfabeto alfabeto) throws SimbNaoAlfabetoException, NaoEstadoException {
		AutomatroFN afn = null;
		
		if (alfabeto.isAlfabeto(simbolo) || simbolo == 'V') {
			if (simbolo == 'V') {
				afn = new AutomatroFN(1, alfabeto);
				afn.setInicio(0);
			} else if (simbolo == 'E') {
				afn = new AutomatroFN(1, alfabeto);
				afn.setInicio(0);
				afn.inserirEstadoFinal(0);
			} else {
				afn = new AutomatroFN(2, alfabeto);
				afn.setInicio(0);
				afn.inserirEstadoFinal(1);
				afn.inserirTransicao(0, simbolo, 1);
			}
		}
		return afn;
	}
	
	public AutomatroFN calcularExp(String expresao) {
		Stack<Character> pilhaOp = new Stack<Character>();
		Stack<AutomatroFN> pilhaAFN = new Stack<AutomatroFN>();
		
		return null;
	}
}

