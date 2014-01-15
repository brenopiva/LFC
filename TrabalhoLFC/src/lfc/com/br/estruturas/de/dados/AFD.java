package lfc.com.br.estruturas.de.dados;

import java.util.ArrayList;

public class AFD {
	private ArrayList<EstadoAFD> estados = new ArrayList<EstadoAFD>();

	public AFD(ArrayList<EstadoAFD> estados) {
		this.estados = estados;
	}

	public ArrayList<EstadoAFD> getEstados() {
		return estados;
	}

	public void setEstados(ArrayList<EstadoAFD> estados) {
		this.estados = estados;
	}

	public boolean verificarPalavra(String palavra) {
		EstadoAFD estado = estados.get(0);
		System.out.println("Estado "+estado.getId());
		for (int i = 0; i < palavra.length(); i++) {
			String simbolo = palavra.substring(i,i+1);
			estado = estado.getProximo(simbolo);
			System.out.println("Estado "+estado.getId()+" com "+simbolo);
		}
		return estado.isFinal();
	}
	
	public EstadoAFD getEstadoInicial(){
		for(int i = 0; i < estados.size(); i++){
			if(estados.get(i).isInicial()){
				return estados.get(i);
			}
		}
		return null;
	}
}
