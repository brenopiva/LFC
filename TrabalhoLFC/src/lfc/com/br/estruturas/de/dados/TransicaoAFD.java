package lfc.com.br.estruturas.de.dados;

public class TransicaoAFD {

	private EstadoAFD estado;
	private String simbolo;

	public TransicaoAFD(EstadoAFD estado, String simbolo) {
		this.estado = estado;
		this.simbolo = simbolo;
	}

	public EstadoAFD getEstado() {
		return estado;
	}

	public void setEstado(EstadoAFD estado) {
		this.estado = estado;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

}
