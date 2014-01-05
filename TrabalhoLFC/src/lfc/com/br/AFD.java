package lfc.com.br;

import java.util.ArrayList;

public class AFD {
	private ArrayList<Estado> estados = new ArrayList<Estado>();

	public AFD(ArrayList<Estado> estados) {
		this.estados = estados;
	}

	public ArrayList<Estado> getEstados() {
		return estados;
	}

	public void setEstados(ArrayList<Estado> estados) {
		this.estados = estados;
	}

	public boolean verificarPalavra(String palavra) {
		Estado estado = estados.get(0);
		System.out.println("Estado "+estado.getId());
		for (int i = 0; i < palavra.length(); i++) {
			String simbolo = palavra.substring(i,i+1);
			estado = estado.getProximo(simbolo);
			System.out.println("Estado "+estado.getId()+" com "+simbolo);
		}
		return estado.isFinal();
	}

}
