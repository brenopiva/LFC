package lfc.com.br.estruturas.de.dados;

import java.util.ArrayList;

public class AFN {
	private ArrayList<EstadoAFN> estados = new ArrayList<EstadoAFN>();

	public AFN(ArrayList<EstadoAFN> estados) {
		this.estados = estados;
	}

	public ArrayList<EstadoAFN> getEstados() {
		return estados;
	}

	public void setEstados(ArrayList<EstadoAFN> estados) {
		this.estados = estados;
	}

	public EstadoAFN getEstadoInicial() {
		for (int i = 0; i < estados.size(); i++) {
			if (estados.get(i).isInicial()) {
				return estados.get(i);
			}
		}
		return null;
	}
}
