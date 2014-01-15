package lfc.com.br.estruturas.de.dados;

import java.util.ArrayList;

public class AFNtoAFD {
	private AFN afn;
	private AFD afd;

	public AFNtoAFD(AFN afn, AFD afd) {
		this.afn = afn;
		this.afd = afd;
//		AFNtoAFD();
	}

	public AFN getAfn() {
		return afn;
	}

	public void setAfn(AFN afn) {
		this.afn = afn;
	}

	public AFD getAfd() {
		return afd;
	}

	public void setAfd(AFD afd) {
		this.afd = afd;
	}
	
//	public AFD AFNtoAFD(){
//		for(int i = 0; i <this.afn.getEstados().size(); i++){
//			afn.getEstados().get(i).setId(String.valueOf(i));
//		}
//		ArrayList<EstadoAFD> estados = new ArrayList<EstadoAFD>();
//		for(int i = 0; i <afn.getEstados().size(); i++){
//			for(int j = 0; j < afn.getEstados().get(i).getTransicoes().size(); j++){
//				String id = "";
//				for(int k = 0; k < afn.getEstados().get(i).getTransicoes().get(j).getEstados().size(); k++){
//					id = id+afn.getEstados().get(i).getTransicoes().get(j).getEstados().get(k).getId();
//				}
//				EstadoAFD estadoAFD = new EstadoAFD(id, false, false);
//				estados.add(estadoAFD);
//			}
//		}
//		return null;
//	}

}
