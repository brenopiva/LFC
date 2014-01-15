package lfc.com.br.estruturas.de.dados;

import java.util.ArrayList;

public class EstadoAFD {
	private ArrayList<TransicaoAFD> transicoes = new ArrayList<TransicaoAFD>();
	private boolean isFinal;
	private boolean isInicial;
	private String id;

	public EstadoAFD(String id, boolean isInicial, boolean isFinal) {
		this.isFinal = isFinal;
		this.id = id;
		this.isInicial = isInicial;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EstadoAFD(ArrayList<TransicaoAFD> transicoes) {
		this.transicoes = transicoes;
	}

	public void addTransicao(TransicaoAFD transicao) {
		transicoes.add(transicao);
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	public boolean isInicial() {
		return isInicial;
	}

	public void setInicial(boolean isInicial) {
		this.isInicial = isInicial;
	}

	public ArrayList<TransicaoAFD> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(ArrayList<TransicaoAFD> transicoes) {
		this.transicoes = transicoes;
	}

	public EstadoAFD getProximo(String simbolo) {
		for (int i = 0; i < transicoes.size(); i++) {
			if (transicoes.get(i).getSimbolo().equals(simbolo)) {
				return transicoes.get(i).getEstado();
			}
		}
		return null;
	}

}
