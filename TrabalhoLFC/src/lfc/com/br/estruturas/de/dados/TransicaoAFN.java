package lfc.com.br.estruturas.de.dados;

import java.util.ArrayList;

public class TransicaoAFN {

	private ArrayList<EstadoAFN> estados;
	private String simbolo;

	public TransicaoAFN(ArrayList<EstadoAFN> estados, String simbolo) {
		this.estados = estados;
		this.simbolo = simbolo;
	}

	public ArrayList<EstadoAFN> getEstados() {
		return estados;
	}

	public void setEstado(ArrayList<EstadoAFN> estados) {
		this.estados = estados;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

}
