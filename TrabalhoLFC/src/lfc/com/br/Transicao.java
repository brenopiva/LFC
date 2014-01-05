package lfc.com.br;

public class Transicao {

	private Estado estado;
	private String simbolo;

	public Transicao(Estado estado, String simbolo) {
		this.estado = estado;
		this.simbolo = simbolo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

}
