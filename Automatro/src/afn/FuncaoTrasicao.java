package afn;

import alfabeto.Alfabeto;
import alfabeto.SimbNaoAlfabetoException;

/**
 * Representa uma função de transição de um automatro finito deterministico.
 */
public class FuncaoTrasicao {
	private Integer[][] funcao;
	private Alfabeto alf;
	private int qtdaEstado;
	
	public FuncaoTrasicao(int qtdaEstAut, Alfabeto alfabeto) {
		funcao = new Integer[qtdaEstAut][alfabeto.getTamAlf()];
		alf = alfabeto;
		qtdaEstado = qtdaEstAut;
	}
	
	public Integer[][] getFuncao() {
		return funcao;
	}

	public int getQtdaEstado() {
		return qtdaEstado;
	}

	/**
	 * Muda o estado atual em função do simbolo que pertence o alfabeto.
	 * @param estado é o estado atual
	 * @param simbolo simbolo que contem no alfalbeto
	 * @return um novo estado que correspode ao simbolo.
	 * @throws NaoEstadoException 
	 * @throws SimbNaoAlfabetoException 
	 */
	public Integer transicao(int estado, char simbolo) throws NaoEstadoException, SimbNaoAlfabetoException {
		isEstado(estado);
		return funcao[estado][alf.getChave(simbolo)];
	}

	/**
	 * Confugura a transiçao de um estado.
	 * @param estado é o estado atual
	 * @param simbolo o caracter de entrada para ir para o proximo estado
	 * @param estadoFut o estado que se tornará atual passando o simbolo correspondente.
	 * @throws SimbNaoAlfabetoException 
	 * @throws NaoEstadoException 
	 */
	public void setTransicao(int estado, char simbolo, int estadoFut) throws SimbNaoAlfabetoException, NaoEstadoException {
		isEstado(estadoFut);
		isEstado(estado);
		funcao[estado][alf.getChave(simbolo)] = estadoFut;
	}
	
	private void isEstado(int estado) throws NaoEstadoException {
		if(!(estado >= 0 && estado < qtdaEstado))
			throw new NaoEstadoException();
	}
}
