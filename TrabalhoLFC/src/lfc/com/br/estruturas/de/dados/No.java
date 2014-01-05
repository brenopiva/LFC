package lfc.com.br.estruturas.de.dados;

public class No {
	/** Objeto guardado no No */
	private Object info;

	/** Referencia para o proximo No */
	private No proximo;

	public No(Object info) {
		this(info, null);
	}
	
	public No(Object info, No proximo) {
		this.info = info;
		this.proximo = proximo;
	}


	public Object getInfo() {
		return this.info;
	}

	public No getProximo() {
		return this.proximo;
	}

	public void setProximo(No proximoNo) {
		this.proximo = proximoNo;
	}
}
