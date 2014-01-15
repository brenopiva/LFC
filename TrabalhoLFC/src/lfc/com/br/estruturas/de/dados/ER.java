package lfc.com.br.estruturas.de.dados;

import java.util.ArrayList;

import lfc.com.br.estruturas.de.dados.AFD;
import lfc.com.br.estruturas.de.dados.AFN;
import lfc.com.br.estruturas.de.dados.EstadoAFD;
import lfc.com.br.estruturas.de.dados.EstadoAFN;
import lfc.com.br.estruturas.de.dados.TransicaoAFD;
import lfc.com.br.estruturas.de.dados.TransicaoAFN;

public class ER {
	private String er;

	public ER(String er) {
		this.er = er;
	}
	// Criando um automato base com apenas dois estados e uma transição
	public AFN criarAutomatoBase(String caracter){
		EstadoAFN e1 = new EstadoAFN(null, true, false);
		EstadoAFN e2 = new EstadoAFN(null, false, true);
		ArrayList<EstadoAFN> conjuntoDeEstados = new ArrayList<EstadoAFN>();
		conjuntoDeEstados.add(e2);
		TransicaoAFN t1 = new TransicaoAFN(conjuntoDeEstados, caracter);
		e1.addTransicao(t1);
		ArrayList<EstadoAFN> estados = new ArrayList<EstadoAFN>();
		estados.add(e1);
		estados.add(e2);
		AFN afn = new AFN(estados);
		return afn;
	}
	// Operação de União entre dois automatos
	public AFN operacaoUniao(AFN afn, AFN afn2){
		// Criando novo estado inicial
		EstadoAFN estado = new EstadoAFN(null, true, false);
		EstadoAFN estadoInicialAFN = afn.getEstadoInicial();
		// Antigo estado inicial não é mais inicial
		estadoInicialAFN.setInicial(false);
		EstadoAFN estadoInicialAFN2 = afn2.getEstadoInicial();
		// Antigo estado inicial não é mais inicial
		estadoInicialAFN2.setInicial(false);
		ArrayList<EstadoAFN> conjuntoDeEstados = new ArrayList<EstadoAFN>();
		conjuntoDeEstados.add(estadoInicialAFN);
		conjuntoDeEstados.add(estadoInicialAFN2);
		// Criando transição para os antigos estados iniciais
		TransicaoAFN t1 = new TransicaoAFN(conjuntoDeEstados, "e");
		// Adicionando transições no novo estado inicial
		estado.addTransicao(t1);
		// Novo conjunto de estados com todos os estados anteriores mais o novo estado inicial
		ArrayList<EstadoAFN> estados = new ArrayList<EstadoAFN>();
		estados.add(estado);
		estados.addAll(afn.getEstados());
		estados.addAll(afn2.getEstados());
		AFN retorno = new AFN(estados);
		return retorno;
	}
	// Operação de concatenação entre dois automatos
	public AFN operacaoConcatenacao(AFN afn, AFN afn2){
		EstadoAFN estadoInicialAFN2 = afn2.getEstadoInicial();
		// Antigo estado inicial do afn2 não é mais inicial
		estadoInicialAFN2.setInicial(false);
		ArrayList<EstadoAFN> conjuntoDeEstados = new ArrayList<EstadoAFN>();
		conjuntoDeEstados.add(estadoInicialAFN2);
		// Criando uma transição para o antigo estado inicial do afn2
		// que será adicionada em todos os antigos estados finais do afn
		TransicaoAFN t1 = new TransicaoAFN(conjuntoDeEstados, "e");
		for(int i = 0; i < afn.getEstados().size(); i++){
			EstadoAFN estadoAtual = afn.getEstados().get(i); 
			if(estadoAtual.isFinal()){
				estadoAtual.addTransicao(t1);
				estadoAtual.setFinal(false);
			}
		}
		ArrayList<EstadoAFN> estados = new ArrayList<EstadoAFN>();
		estados.addAll(afn.getEstados());
		estados.addAll(afn2.getEstados());
		AFN retorno = new AFN(estados);
		return retorno;
	}
	// Operação de estrela
	public AFN operacaoEstrela(AFN afn){
		// Criando um novo estado inicial que também é final
		EstadoAFN novoEstadoInicial = new EstadoAFN(null, true, true);
		EstadoAFN antigoEstadoInicial = afn.getEstadoInicial();
		// Antigo estado inicial não é mais inicial
		antigoEstadoInicial.setInicial(false);
		ArrayList<EstadoAFN> conjuntoDeEstados = new ArrayList<EstadoAFN>();
		conjuntoDeEstados.add(antigoEstadoInicial);
		TransicaoAFN t1 = new TransicaoAFN(conjuntoDeEstados, "e");
		// Novo estado inicial tem transição para o antigo estado inicial
		novoEstadoInicial.addTransicao(t1);
		ArrayList<EstadoAFN> conjuntoDeEstados2 = new ArrayList<EstadoAFN>();
		conjuntoDeEstados.add(novoEstadoInicial);
		TransicaoAFN t2 = new TransicaoAFN(conjuntoDeEstados2, "e");
		// Todos os estados finais têm transições para o novo estado inicial
		// e não são mais finais
		for(int i = 0; i < afn.getEstados().size(); i++){
			EstadoAFN estado = afn.getEstados().get(i);
			if(estado.isFinal()){
				estado.addTransicao(t2);
				estado.setFinal(false);
			}
		}
		
		ArrayList<EstadoAFN> estados = new ArrayList<EstadoAFN>();
		estados.add(novoEstadoInicial);
		estados.addAll(afn.getEstados());
		AFN retorno = new AFN(estados);
		return retorno;
	}

	public void posfixaTOafn(String erPosfixa){
		PilhaEnc pilha = new PilhaEnc();
		for (int i = 0; i < erPosfixa.length(); i++) {
			String caracter = erPosfixa.substring(i, i + 1);
			if(isLetra(caracter)){
				AFN afn = criarAutomatoBase(caracter);
				pilha.empilhar(afn);
			}else{
				if(caracter.equals("U")){
					AFN afn = (AFN) pilha.topo();
					pilha.desempilhar();
					AFN afn2 = (AFN) pilha.topo();
					pilha.desempilhar();
					AFN afn3 = operacaoUniao(afn, afn2);
					pilha.empilhar(afn3);
				}
				else if(caracter.equals("*")){
					AFN afn = (AFN) pilha.topo();
					pilha.desempilhar();
					AFN afn2 = operacaoEstrela(afn);
					pilha.empilhar(afn2);
				}
				else if(caracter.equals(".")){
					AFN afn = (AFN) pilha.topo();
					pilha.desempilhar();
					AFN afn2 = (AFN) pilha.topo();
					pilha.desempilhar();
					AFN afn3 = operacaoConcatenacao(afn, afn2);
					pilha.empilhar(afn3);
				}
			}
		}
	}
	
	public void infixaTOposfixa(String er) {
		PilhaEnc pilha = new PilhaEnc();
		String expressao = er;
		String expressaoSaida = "";
		for (int i = 0; i < expressao.length(); i++) {
			String caracter = expressao.substring(i, i + 1);
			if (isLetra(caracter)) {
				expressaoSaida = expressaoSaida + caracter;
			} else if (caracter.equals("(")) {
				pilha.empilhar(caracter);
			} else if (caracter.equals(")")) {
				while (!pilha.topo().equals("(")) {
					expressaoSaida = expressaoSaida+pilha.topo();
					pilha.desempilhar();
				}
				pilha.desempilhar();
			} else if (isSimbolo(caracter)) {
				while (!pilha.vazia() && temMaiorPrioridade(String.valueOf(pilha.topo()),
								caracter)) {
					if (isSimbolo(String.valueOf(pilha.topo()))) {
						expressaoSaida = expressaoSaida+pilha.topo();
						pilha.desempilhar();
					} else {
						break;
					}
				}
				pilha.empilhar(caracter);
			}
		}
		for (int i = 0; i <= pilha.tamanho(); i++) {
			if (isSimbolo(String.valueOf(pilha.topo()))) {
				expressaoSaida = expressaoSaida + pilha.topo();
			}
			pilha.desempilhar();
		}
		System.out.println(expressaoSaida);
	}

	public boolean isLetra(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")
				|| s.equals("(") || s.equals(")")) {
			return false;
		}
		return true;
	}

	public boolean isSimbolo(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
			return true;
		}
		return false;
	}

	public boolean temMaiorPrioridade(String p1, String p2) {
		int prioridade1;
		int prioridade2;

		if (p1.equals("(") || p1.equals(")")) {
			prioridade1 = 4;
		} else if (p1.equals("*")) {
			prioridade1 = 3;
		} else if (p1.equals(".")){
			prioridade1 = 2;
		}else {
			prioridade1 = 1; // operador +
		}

		if (p2.equals("(") || p2.equals(")")) {
			prioridade2 = 4;
		} else if (p2.equals("*")) {
			prioridade2 = 3;
		} else if (p2.equals(".")){
			prioridade2 = 2;
		}else {
			prioridade2 = 1; // operador +
		}

		return (prioridade1 >= prioridade2);
	}
}
