package afn;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import alfabeto.Alfabeto;
import alfabeto.SimbNaoAlfabetoException;

/**
 * Representa a função de transição de um automatro finito não deterministico.
 * @author Rafael
 *
 */
public class FuncaoTrasicaoAfn {
	private Object[][] funcao;
	private Alfabeto alf;
	private Collection<Integer> conjEstFinal;
	private int qtdaEstados;
	
	public FuncaoTrasicaoAfn(int qtdaEst, Alfabeto alfabeto) {
		alf = alfabeto;
		qtdaEstados = qtdaEst;
		funcao = new Object[qtdaEst][alf.getTamAlf()];
		conjEstFinal = new HashSet<Integer>();
	}
	
	public Object[][] getFuncao() {
		return funcao;
	}

	public Alfabeto getAlf() {
		return alf;
	}
	
	public int getQtdaEstados() {
		return qtdaEstados;
	}

	public void setQtdaEstados(int qtdaEstados) {
		this.qtdaEstados = qtdaEstados;
	}
	
	public Collection<Integer> getConjEstFinal() {
		return conjEstFinal;
	}
	public void setConjEstFinal(Collection<Integer> conjEstFinal) {
		this.conjEstFinal = conjEstFinal;
	}
	public boolean inserirEstadoFinal(int estado) {
		return getConjEstFinal().add(estado);
	}
	
	public boolean inserirEstadosFinal(Collection<Integer> estados) {
		return getConjEstFinal().addAll(estados);
	}

	public Collection<Integer> transicao(int estado, char simbolo) throws SimbNaoAlfabetoException {
		return (Collection<Integer>) funcao[estado][alf.getChave(simbolo)];
	}
	
	public void setTransicao(int estado, char simbolo, int estadoFut) throws SimbNaoAlfabetoException, NaoEstadoException {
		if (estado < getQtdaEstados() && estadoFut < getQtdaEstados()) {
			if(isLinguagemVazia(estado, simbolo)) {
				Collection<Integer> conjunto = new HashSet<Integer>();
				conjunto.add(estadoFut);
				funcao[estado][alf.getChave(simbolo)] = conjunto;
			} else {
				((Collection<Integer>) transicao(estado, simbolo)).add(estadoFut);
			}
		} else
			throw new NaoEstadoException();
	}
	
	/**
	 * Adiciona uma transição.
	 * @param estado estado atual.
	 * @param simbolo simbolo a ser passado.
	 * @param cojunto novo conjunto de estados.
	 * @throws SimbNaoAlfabetoException
	 */
	public void setTransicao(int estado, char simbolo, Collection<Integer> cojunto) throws SimbNaoAlfabetoException {
		funcao[estado][alf.getChave(simbolo)] = cojunto;
	}
	
	protected boolean isLinguagemVazia(int estado, char simbolo) throws SimbNaoAlfabetoException {
		return transicao(estado, simbolo) == null || transicao(estado, simbolo).isEmpty();
	}
	
	/**
	 * add um estado ao conjunto de estados final.
	 * @param estado o estado a ser adicionado.
	 */
	public void addEstFinal(Integer estado) {
		this.conjEstFinal.add(estado);
	}
	
	/**
	 * add uns estados ao conjunto de estados final.
	 * @param estados os estados a ser adicionado.
	 */
	public void addEstFinal(LinkedList<Integer> estados) {
		for (int i = 0; i < estados.size(); i++) {
			conjEstFinal.add(estados.get(i));
		}
	}
	
	/**
	 * Deleta todos os estado deste conjunto de estados.
	 */
	public void limparConjEstFinal() {
		conjEstFinal.clear();
	}
	
	/**
	 * Apenas copiar a função passada, sobrepondo a existente
	 * (substitui os estado atual passado correspodentes ao de b).
	 * @param b função a ser copiada. Deve ser menor ou igual a função corrente.
	 * @return 
	 */
	public boolean copiarFuncaoTransicao(FuncaoTrasicaoAfn b) {
		if (this.getQtdaEstados() >= b.getQtdaEstados()) {
			for (int i = 0; i < b.getQtdaEstados(); i++) {
				for (int j = 0; j < alf.getTamAlf(); j++) {
					funcao[i][j] = b.getFuncao()[i][j];
				}
			}
			//this.inserirEstadosFinal(b.getConjEstFinal());
			return true;
		}
		return false;
	}
	
	/**
	 * Adiciona uma novar função de trasição. E renomea os estado da função b.
	 * @param b função de transição a ser adicionada.
	 * @param conjEst novo conjunto de estrunção.
	 */
	public void adicionarFuncTransicao(int qtdaEstA, FuncaoTrasicaoAfn b, Collection<Integer> conjEst) {
		for (int i = 0; i < b.qtdaEstados; i++) {
			for (int j = 0; j < getAlf().getTamAlf(); j++) {
				getFuncao()[qtdaEstA][j] = conjEst;
			}
		}
	}
	
	public void printFuncaoTransicao() throws SimbNaoAlfabetoException {
		System.out.print("[EST\\ALF]");
		for (int i = 0; i < getAlf().getTamAlf(); i++) {
			System.out.print(alf.getSimbolo(i)+"\t\t");
		}
		System.out.println();
		for (int i = 0; i < this.getQtdaEstados(); i++) {
			System.out.print(i+"\t");
			for (int j = 0; j < getAlf().getTamAlf(); j++) {
				printEstado(transicao(i, alf.getSimbolo(j)));
				System.out.print("\t\t");
			}
			System.out.println();
		}
		
		Integer[] estadofim = new Integer[conjEstFinal.size()];
		conjEstFinal.toArray(estadofim);
		System.out.print("Estados finais:");
		System.out.print(conjEstFinal.toString()+"\n");
		System.out.println();
	}

	public void printEstado(Collection<Integer> a) {
		if (a != null) {
			Integer[] conjEstado = new Integer[a.size()];
			a.toArray(conjEstado);
			for (int i = 0; i < a.size(); i++) {
				if(i == a.size()-1)
					System.out.print(conjEstado[i]);
				else
					System.out.print(conjEstado[i]+",");
			}
		}
	}
	
}
