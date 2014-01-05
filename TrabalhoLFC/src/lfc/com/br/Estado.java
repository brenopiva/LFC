package lfc.com.br;

import java.util.ArrayList;

public class Estado {
	private ArrayList<Transicao> transicoes = new ArrayList<Transicao>();
	private boolean isFinal;
	private int id;

	public Estado(boolean isFinal, int id) {
		this.isFinal = isFinal;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Estado(ArrayList<Transicao> transicoes) {
		this.transicoes = transicoes;
	}

	public void addTransicao(Transicao transicao) {
		transicoes.add(transicao);
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public ArrayList<Transicao> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(ArrayList<Transicao> transicoes) {
		this.transicoes = transicoes;
	}

	public Estado getProximo(String simbolo) {
		for (int i = 0; i < transicoes.size(); i++) {
			if (transicoes.get(i).getSimbolo().equals(simbolo)) {
				return transicoes.get(i).getEstado();
			}
		}
		return null;
	}

}
