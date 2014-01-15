package lfc.com.br.estruturas.de.dados;

import java.util.ArrayList;

public class EstadoAFN {
	private ArrayList<TransicaoAFN> transicoes = new ArrayList<TransicaoAFN>();
	private boolean isFinal;
	private boolean isInicial;
	private String id;

	public EstadoAFN(String id, boolean isInicial, boolean isFinal) {
		this.isFinal = isFinal;
		this.id = id;
		this.isInicial = isInicial;
	}

	public EstadoAFN(ArrayList<TransicaoAFN> transicoes) {
		this.transicoes = transicoes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void addTransicao(TransicaoAFN transicao) {
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

	public ArrayList<TransicaoAFN> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(ArrayList<TransicaoAFN> transicoes) {
		this.transicoes = transicoes;
	}

}
