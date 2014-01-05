package lfc.com.br;

public class Arvore {

	private No raiz;

	public Arvore() {
		
	}

	public class No {
		private No filhoEsquerdo;
		private No filhoDireito;
		private No pai;
		private Object dado;

		public No(){}
		
		public void adicionarFilhoEsquerdo(No esquerdo) {
			this.filhoEsquerdo = esquerdo;
		}

		public void adicionarFilhoDireito(No direitor) {
			this.filhoDireito = direitor;
		}
	}
}
