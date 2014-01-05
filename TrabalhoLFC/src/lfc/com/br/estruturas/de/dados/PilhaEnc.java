package lfc.com.br.estruturas.de.dados;

public class PilhaEnc {
	
	/** Numero de objetos contidos na pilha */
	private int tamanho;
	
	/** Referencia para o No que esta no topo da pilha */
	private No topo;
	
	//Construtor da Pilha, inicializando o taanho em 0 e a topo em null
	public PilhaEnc(){
		tamanho = 0;
		topo = null;
	}
	
	/**
	 * Retorna true caso o tamanho seja 0, ou seja, nenhum Nodo na Pilha
	 */
	public boolean vazia() {
		return tamanho == 0;
	}
	
	/**
	 * Sempre retorna false porque nao da para prever quando ha espaco disponivel
	 * na memoria, logo supomos que nunca estara cheia.
	 */

	public boolean cheia() {
		return false;
	}

	/** 
	 * Retorna o tamanho da pilha.
	 */

	public int tamanho() {
		return this.tamanho;
	}	
	
	/**
	 * Coloca um novo objeto no topo da pilha, de forma semelhante a uma insercao na cabeca
	 * de uma lista simplesmente encadeada. 
	 */

	public boolean empilhar(Object info) {
		this.topo = new No(info, this.topo);
		this.tamanho++;
		return true;
	}


	public Object desempilhar() {
		No antigoTopo = this.topo;
		this.topo = this.topo.getProximo();		
		this.tamanho--;
		antigoTopo.setProximo(null);
		return antigoTopo.getInfo();
	}	

	/** 
	 * Apaga todos os objetos contidos na pilha, fazendo com que cada
	 * No deixe de referenciar o proximo para facilitar a coleta de lixo.
	 */

	public void apagar() {
		if(!vazia()){
			while(this.topo != null){
				No noLiberar = this.topo;
				this.topo = this.topo.getProximo();
				noLiberar.setProximo(null);
			}
			this.tamanho = 0;			
		}
	}
	

	public Object topo() {		
		if(!vazia()){
			return topo.getInfo();
		} else {
			throw new IllegalStateException("Underflow da Pilha");
		}
	}	
}
