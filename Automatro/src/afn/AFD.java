package afn;

import java.util.Collection;

import alfabeto.Alfabeto;
import alfabeto.SimbNaoAlfabetoException;

public class AFD extends AutomatroFN {

	public AFD() {
		super();
	}

	public AFD(int qtdaEstados, Alfabeto alfabeto) {
		super(qtdaEstados, alfabeto);
	}
	
	@Override
	public void setInicio(Integer inicio) {
		super.setInicio(inicio);
	}
	
	@Override
	public void inserirTransicao(int estado, char simbolo,Collection<Integer> conjutoEst) 
			throws SimbNaoAlfabetoException, NaoEstadoException {
		super.inserirTransicao(estado, simbolo, conjutoEst);
	}
	
	@Override
	public void inserirTransicao(int estado, char simbolo, int estadoFut)
			throws SimbNaoAlfabetoException, NaoEstadoException {
		super.inserirTransicao(estado, simbolo, estadoFut);
	}
	
	@Override
	public void printAFN() throws SimbNaoAlfabetoException {
		super.printAFN();
	}
	
	/**
	 * Fora do escopo desta classe.
	 */
	@Override
	public AFD transfAFNemAFD() {
		return null;
	}
	
	/**
	 * Fora do escopo desta classe.
	 */
	@Override
	public AutomatroFN uniao(AutomatroFN b) throws SimbNaoAlfabetoException,
			NaoEstadoException {
		return null;
	}
	
	/**
	 * Fora do escopo desta classe.
	 */
	@Override
	public AutomatroFN concatenacao(AutomatroFN b)
			throws SimbNaoAlfabetoException, NaoEstadoException {
		return null;
	}
	
	/**
	 * Fora do escopo desta classe.
	 */
	@Override
	public AutomatroFN estrela() throws SimbNaoAlfabetoException,
			NaoEstadoException {
		return null;
	}

}
