package afn;

import java.util.Collection;

public class ConjuntoDEstadoEstadoAtual {
	
	private Collection<Integer> cojuEstado;
	private int estado;
	
	public ConjuntoDEstadoEstadoAtual() {
	}

	public Collection<Integer> getCojuEstado() {
		return cojuEstado;
	}

	public int getEstado() {
		return estado;
	}

	public void setCojuEstado(Collection<Integer> cojuEstado) {
		this.cojuEstado = cojuEstado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
