package expresao.regular;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import afn.AutomatroFN;
import afn.NaoEstadoException;
import alfabeto.Alfabeto;
import alfabeto.SimbNaoAlfabetoException;

public class CriarAFNExcutar {
	
	private Alfabeto alf;
	
	public CriarAFNExcutar(Alfabeto alf) {
		alf = alf;
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
	
	public AutomatroFN calcularExp(String expresao) throws SimbNaoAlfabetoException, NaoEstadoException {
		Stack<Character> pilhaOp = new Stack<Character>();
		Stack<AutomatroFN> pilhaAFN = new Stack<AutomatroFN>();
		AutomatroFN afn;
		char c;
		
		for (int i = 0; i < expresao.length(); i++) {
			c = expresao.charAt(i);
			if (c != '(') {
				if (isOperador(c))
					pilhaOp.push(c);
				else if (c != ')') {
					afn = criarAFN(c, alf);
					pilhaAFN.push(afn);
				} else {
					calcular(pilhaOp, pilhaAFN, c);
				}
			}
		}
		
		return null;
	}

	private void calcular(Stack<Character> pilhaOp,
			Stack<AutomatroFN> pilhaAFN, char c)
			throws SimbNaoAlfabetoException, NaoEstadoException {
		AutomatroFN a, b;
		char op;
		switch (c) {
			case '+':
				a = pilhaAFN.pop();
				op = pilhaOp.pop();
				b = pilhaAFN.pop();
				pilhaAFN.push(a.uniao(b));
				break;
			case '.': 
				a = pilhaAFN.pop();
				op = pilhaOp.pop();
				b = pilhaAFN.pop();
				pilhaAFN.push(a.concatenacao(b));
				break;
			case '*':
				a = pilhaAFN.pop();
				op = pilhaOp.pop();
				pilhaAFN.push(a.estrela());
				break;
		}
	}
	
	private boolean isOperador(char c) {
		if (c == '+')
			return true;
		else if (c == '.') 
			return false;
		else if (c == '*') 
			return true;
		return false;
	}
}

