package afn;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.IntArrayData;

import alfabeto.Alfabeto;
import alfabeto.SimbNaoAlfabetoException;

/**
 * Representa um automatro finito não determinisco.
 * @author Rafael
 *
 */
public class AutomatroFN {
	private Integer inicio;
	private FuncaoTrasicaoAfn funcao;
	private LinkedList<Collection<Integer>> conjDasPartes;
	private Collection<Integer> novosConjTransVazia;
	
	public AutomatroFN() {
		conjDasPartes = new LinkedList<Collection<Integer>>();
		//novosConjTransVazia = new HashSet<Integer>();
	}
	
	public AutomatroFN(int qtdaEstados, Alfabeto alfabeto) {
		conjDasPartes = new LinkedList<Collection<Integer>>();
		novosConjTransVazia = new HashSet<Integer>();
		funcao = new FuncaoTrasicaoAfn(qtdaEstados, alfabeto);
	}

	public FuncaoTrasicaoAfn getFuncao() {
		return funcao;
	}
	public Alfabeto getAlfabeto() {
		return getFuncao().getAlf();
	}
	public Integer getInicio() {
		return inicio;
	}
	public int getQtdaEstados() {
		return getFuncao().getQtdaEstados();
	}
	public void setQtdaEstados(int qtdaEstados) {
		getFuncao().setQtdaEstados(qtdaEstados);
	}
	public Collection<Integer> getEstadoFinal(){
		return this.getFuncao().getConjEstFinal();
	}
	public void setEstadosFinal(Collection<Integer> conjEstados) {
		this.getFuncao().setConjEstFinal(conjEstados);
	}
	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}
	
	public boolean inserirEstadoFinal(int estado) {
		return getFuncao().inserirEstadoFinal(estado);
	}
	
	public boolean inserirEstadosFinal(Collection<Integer> estados) {
		return getFuncao().inserirEstadosFinal(estados);
	}
	
	public void inserirTransicao(int estado, char simbolo, int estadoFut) throws SimbNaoAlfabetoException, NaoEstadoException {
		getFuncao().setTransicao(estado, simbolo, estadoFut);
	}
	
	public void inserirTransicao(int estado, char simbolo, Collection<Integer> conjutoEst) throws SimbNaoAlfabetoException, NaoEstadoException {
		getFuncao().setTransicao(estado, simbolo, conjutoEst);
	}

	/**
	 * Cria uma nova fuçnão de transição para este automatro. 
	 * @param qtdaEstados quantidade de estado da nova função de transição.
	 * @param alfabeto o alfabeto que este automatro aceita.
	 */
	public void criarFuncaoTransicao(int qtdaEstados, Alfabeto alfabeto) {
		funcao = new FuncaoTrasicaoAfn(qtdaEstados, alfabeto);
	}
	
	/**
	 * Faz a operação de união entre dois AFN.
	 * @param b automatro que unirá ao corrente.
	 * @return o automatro que corresponde a união de b com o corrente.
	 * @throws NaoEstadoException, SimbNaoAlfabetoException 
	 */
	public AutomatroFN uniao(AutomatroFN b) throws SimbNaoAlfabetoException, NaoEstadoException {
		if(igualAlfabeto(b)) {
			int quantEstadoNovoAFN = this.getQtdaEstados()+b.getQtdaEstados()+1;
			int novoEstInicial = quantEstadoNovoAFN-1;
			AutomatroFN novoAutomatro = new AutomatroFN(quantEstadoNovoAFN, this.getAlfabeto());
			novoAutomatro.setInicio(novoEstInicial);
			novoAutomatro.getFuncao().copiarFuncaoTransicao(this.getFuncao());
			novoAutomatro.adicionarFuncTransicao(this.getQtdaEstados(), b);
			
			novoAutomatro.inserirTransicao(novoEstInicial, 'E', novoEstInicial);
			novoAutomatro.inserirTransicao(novoEstInicial, 'E', this.getInicio());
			novoAutomatro.inserirTransicao(novoEstInicial, 'E', b.getInicio()+this.getQtdaEstados());

			novoAutomatro.inserirEstadosFinal(this.getEstadoFinal());
			novoAutomatro.inserirEstadosFinal(renomearEstados(b.getEstadoFinal(), this.getQtdaEstados()));
			
			return novoAutomatro;
		}
		return null;
	}
	
	/**
	 * Faz a operação de concatenação entre dois AFN.
	 * @param b automatro a ser concatenado.
	 * @return um automatro como resultado da operação.
	 * @throws NaoEstadoException, SimbNaoAlfabetoException 
	 */
	public AutomatroFN concatenacao(AutomatroFN b) throws SimbNaoAlfabetoException, NaoEstadoException {
		int qtdaEstNovoAFN = this.getQtdaEstados()+b.getQtdaEstados();
		AutomatroFN novoAutomatro = new AutomatroFN(qtdaEstNovoAFN, this.getAlfabeto());
		novoAutomatro.setInicio(this.getInicio());
		novoAutomatro.getFuncao().copiarFuncaoTransicao(this.getFuncao());
		novoAutomatro.adicionarFuncTransicao(this.getQtdaEstados(), b);
		novoAutomatro.inserirTransVaziaEstFinalAInicio(this, b, false);
		novoAutomatro.inserirEstadosFinal(renomearEstados(b.getEstadoFinal(), this.getQtdaEstados()));
		return novoAutomatro;
	}
	
	/**
	 * Realiza a operação estrela no AFN corrente.
	 * @return um AFN com a opreção estrala realizada sobre os mesmo. 
	 * @throws SimbNaoAlfabetoException
	 * @throws NaoEstadoException
	 */
	public AutomatroFN estrela() throws SimbNaoAlfabetoException, NaoEstadoException {
		int qtdaEstNovoAFN = this.getQtdaEstados()+1;
		int inicioNovoAFN = qtdaEstNovoAFN-1; 
		AutomatroFN novoAutomatro = new AutomatroFN(qtdaEstNovoAFN, this.getAlfabeto());
		novoAutomatro.setInicio(inicioNovoAFN);
		novoAutomatro.getFuncao().copiarFuncaoTransicao(this.getFuncao());
		
		novoAutomatro.setEstadosFinal(this.getEstadoFinal());
		
		novoAutomatro.inserirEstadoFinal(inicioNovoAFN);
		
		novoAutomatro.inserirTransVaziaEstFinalAInicio(this, this, true);
		novoAutomatro.inserirTransicao(inicioNovoAFN, 'E', inicioNovoAFN);
		return novoAutomatro;
	}
	
	/**
	 * Transforma um AFN em AFD.
	 * @return
	 * @throws NaoEstadoException 
	 * @throws SimbNaoAlfabetoException 
	 */
	public AFD transfAFNemAFD() throws SimbNaoAlfabetoException, NaoEstadoException {
		Integer qtdaEstAFD =  ((int) Math.pow(2, this.getQtdaEstados()))+1, estadoAFD = 0, est;
		AFD afd = new AFD(qtdaEstAFD, this.getAlfabeto());
		Collection<Integer> estAtual, c = null;
		Map<Collection<Integer>, Integer> mapEstAFD = new Hashtable<Collection<Integer>, Integer>();
		Queue<Collection<Integer>> filaEstAFD = new LinkedList<Collection<Integer>>();
		Collection<Collection<Integer>> conjConjEstAFD = new HashSet<Collection<Integer>>();
		
		estAtual = this.obterTransVazia(this.getInicio());
		estAtual.addAll(obterFeixoTransVazio(estAtual, transfomaConjutoArray(estAtual)));
		conjConjEstAFD.add(estAtual);
		filaEstAFD.add(estAtual);
		mapEstAFD.put(estAtual, 0);
		conjConjEstAFD.add(estAtual);
		conjDasPartes.add(estAtual);
		
		int lingVazia = afd.getQtdaEstados()-1;
		afd.inserirTransicao(lingVazia, 'a', lingVazia);	
		afd.inserirTransicao(lingVazia, 'b', lingVazia);
		afd.setInicio(0);
		
		while (!filaEstAFD.isEmpty()) {
			estAtual = filaEstAFD.poll();
			
			for (int i = 1; i < getAlfabeto().getTamAlf(); i++) {
				c = extrairEstAFNLendo(this, estAtual, i);
				c.addAll(obterFeixoTransVazio(estAtual, transfomaConjutoArray(c)));
				
				if (!isLinguagemVazia(c) && estadoAFD < afd.getQtdaEstados()-1) {
					conjDasPartes.add(c);
					if (!conjConjEstAFD.contains(c)) {
						afd.inserirTransicao(estadoAFD, getSimboloAlfabeto(i), c);
						conjConjEstAFD.add(c);
						filaEstAFD.add(c);		
						mapEstAFD.put(c, estadoAFD);
					} else {
						est = mapEstAFD.get(c);
						if (est == null) 
							afd.inserirTransicao(estadoAFD, getSimboloAlfabeto(i), lingVazia);
						else
							afd.inserirTransicao(estadoAFD, getSimboloAlfabeto(i), est);
					}
					//System.out.println(estadoAFD);
				} else 
					afd.inserirTransicao(estadoAFD, getSimboloAlfabeto(i), lingVazia);
			}
			estadoAFD++;
		}
		//System.out.println(conjDasPartes.toString());
		//ystem.out.println(conjConjEstAFD.toString());
		return afd;
	}
	
	/**
	 * Obtem transições vazia de um conjunto de estados.
	 * @param conjunto
	 * @param novosConj
	 * @return
	 * @throws SimbNaoAlfabetoException
	 */
//	public Collection<Integer> obterFeixoTransVazio(Collection<Integer> conj, Integer[] novosConj, int estadoTransAt) throws SimbNaoAlfabetoException {
//		Collection<Integer> aux = new HashSet<Integer>();
//		Collection<Integer> conjuto = new HashSet<Integer>();
//		Collection<Integer> c = null;
//		Integer[] novosConjuntos = novosConj;
//		if (novosConj != null) {
//			for (int i = 0; i < novosConj.length; i++) 
//				aux.add(novosConj[i]);
//
//			while (existeAlgumaTransVazia(novosConjuntos)) {
//				System.out.print("OPS !!!!!!\n");
//				conjuto.clear();
//				for (int i = 0; i < novosConjuntos.length; i++) {
//					c = obterTransVazia(novosConjuntos[i]);
//					if (c != null ) {
//						aux.addAll(c);
//						if (novosConjuntos[i] != estadoTransAt) {
//							conjuto.addAll(c);
//						}
//					}
//				}
//				for (int i = 0; i < novosConjuntos.length; i++) {
//					novosConjuntos[i] = null;
//				}
//				novosConjuntos = transfomaConjutoArray(conjuto);
//			}
//			
//		}
//		
//		return aux;
//	}
	public Collection<Integer> obterFeixoTransVazio(Collection<Integer> conjunto, 
			Integer[] novosConj) throws SimbNaoAlfabetoException {
		//Integer[] estAt = transfomaConjutoArray(conjunto);
		
		if (existeAlgumaTransVazia(novosConj)) {
			Collection<Integer> aux = null;
			if (conjunto != null) {
			
				for (int i = 0; i < novosConj.length; i++) {
					if (novosConj[i] != null) {
						aux = transicao(novosConj[i], 'E');
						if ( aux != null ) {
							if (!aux.isEmpty()) {
								conjunto.addAll(aux);
								conjunto.addAll(novosConjTransVazia);
							}
						}
					}
				}
				System.out.print(""+novosConjTransVazia.toString());
				if (!novosConjTransVazia.isEmpty())
					if (existeAlgumaTransVazia(transfomaConjutoArray(novosConjTransVazia))) {
						Integer[] a = transfomaConjutoArray(novosConjTransVazia);
						obterFeixoTransVazio(conjunto, a);
					}
				
			}
		}
		return conjunto;
	}
	
	/**
	 * Verifica se alguns dos elementos do conjunto tem transição vazia.
	 * @param conj conjunto a ser verificado.
	 * @return true se existir pelomenos um elemento com transição vazia.
	 * @throws SimbNaoAlfabetoException 
	 */
	private boolean existeAlgumaTransVazia(Integer[] estAt) throws SimbNaoAlfabetoException {
		Collection<Integer> a;
		
		for (int i = 0; i < estAt.length; i++) {
			a = transicao(estAt[i], 'E');
			if (a != null && !a.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtem o feicho transitivo de um conjunto de estado lendo a chave do simbolo 'i'.
	 * @param afn AFN de onde irá extrair os estado.
	 * @param estAtual cojunto de estado que irá ler o simblo 'i'.
	 * @param simbolo char do simbolo do alfabeto.
	 * @return conjunto de estados lendo o simblo.
	 * @throws SimbNaoAlfabetoException 
	 */
	protected Collection<Integer> extrairEstAFNLendo(AutomatroFN afn, Collection<Integer> estAtual, 
			int simbolo) throws SimbNaoAlfabetoException {
		ConjuntoDEstadoEstadoAtual c = new ConjuntoDEstadoEstadoAtual();
		Integer[] estAt = new Integer[estAtual.size()];
		estAtual.toArray(estAt);
		Collection<Integer> conjExtraido = new HashSet<Integer>();
		Collection<Integer> aux;
		
		for (int i = 0; i < estAt.length; i++) {
			aux = afn.transicao(estAt[i], getSimboloAlfabeto(simbolo));
			if (aux != null) {
				conjExtraido.addAll(aux);
				c.setEstado(i);
			}
		}
		c.setCojuEstado(conjExtraido);
		
		return conjExtraido;
	}
	
	/**
	 * Obtem as transições vazia.
	 * @param estado o qual desejamos obter as transições vazia.
	 * @return todos os estado de destino partindo do 'estado' lendo 'E'.
	 * @throws SimbNaoAlfabetoException
	 */
	private Collection<Integer> obterTransVazia(int estado) 
			throws SimbNaoAlfabetoException {
		return transicao(estado, 'E');
	}
	
	private boolean isLinguagemVazia(Collection<Integer> conjutoEstados) throws SimbNaoAlfabetoException {
		return conjutoEstados == null || conjutoEstados.isEmpty();
	}
	
	protected Integer[] transfomaConjutoArray(Collection<Integer> conj) {
		Integer[] estAt = new Integer[conj.size()];
		conj.toArray(estAt);
		return estAt;
	}
	
	/**
	 * Insere transições vazia dos estados finais do automatro 'a' para o inicio 
	 * automatro 'b'. E renomea os estados.
	 * @param b AFN para o qual o estado inicial recebera transições vazia vindo do AFN 'a'.
	 * @param isOperEstrela se esta função esta sendo utilizada para operação estrela.
	 * @throws NaoEstadoException 
	 * @throws SimbNaoAlfabetoException 
	 */
	private void inserirTransVaziaEstFinalAInicio(AutomatroFN estadosFinais, 
			AutomatroFN estadoIncial, boolean isOperEstrela) throws SimbNaoAlfabetoException, NaoEstadoException {
		Integer[] estFinais = new Integer[estadosFinais.getEstadoFinal().size()];
		estadosFinais.getEstadoFinal().toArray(estFinais);
		
		if (isOperEstrela) {
			for (int i = 0; i < estFinais.length; i++) {
				this.inserirTransicao(estFinais[i], 'E', estadoIncial.getInicio());
			}
		} else {
			for (int i = 0; i < estFinais.length; i++) {
				this.inserirTransicao(estFinais[i], 'E', estadoIncial.getInicio()+estadosFinais.getQtdaEstados());
			}
		}
	}
	
	/**
	 * Adiciona uma nova função de trasição. E renomea os estado da função b.
	 * @param b função de transição a ser adicionada.
	 * @param inicio é o final do automatro anterior.
	 * @throws SimbNaoAlfabetoException 
	 * @throws NaoEstadoException 
	 */
	public void adicionarFuncTransicao(int inicio, AutomatroFN automaB) throws SimbNaoAlfabetoException, NaoEstadoException {
		int estadoAtual = inicio;
		Collection<Integer> conjunto;
		for (int i = 0; i < automaB.getQtdaEstados(); i++) {
			for (int j = 0; j < getAlfabeto().getTamAlf(); j++) {
				conjunto = automaB.getFuncao().transicao(i, getSimboloAlfabeto(j));
				this.inserirTransicao( estadoAtual, this.getSimboloAlfabeto(j), 
						renomearEstados(conjunto, inicio) );
			}
			estadoAtual++;
		}
	}
	
	/**
	 * Renomea todos os estado do cojunto.
	 * @param colec conjunto de estados a ser renomeado.
	 * @param fatorAtualizacao vai adicionar aos estado este fator.
	 * @return o conjunto atualizado. 
	 */
	private Collection<Integer> renomearEstados(Collection<Integer> colec, int fatorAtualizacao) {
		Collection<Integer> colecao = new HashSet<Integer>();
		if (colec != null) {
			colecao.addAll(colec);
			Integer[] estados = new Integer[colecao.size()];
			colecao.toArray(estados);
			for (int i = 0; i < estados.length; i++) {
				estados[i] = estados[i] + fatorAtualizacao;
			}
			colecao.clear();
			for (int i = 0; i < estados.length; i++) {
				colecao.add(estados[i]);
			}
		}
		return colecao;
	}

	/**
	 * Faze a transição de estados.
	 * @param estado estado atual.
	 * @param simbolo simbolo de entrada.
	 * @return o conjunto de estados.
	 * @throws SimbNaoAlfabetoException
	 */
	public Collection<Integer> transicao(int estado, char simbolo) throws SimbNaoAlfabetoException {
		return this.getFuncao().transicao(estado, simbolo);
	}
	
	/**
	 * obtem o simbolo referente a chave.
	 * @param chave chave do simbolo.
	 * @return o caracterer que representa o simbolo do alfabeto.
	 */
	public char getSimboloAlfabeto(int chave) {
		return getAlfabeto().getSimbolo(chave);
	}
	
	/**
	 * Imprime o AFN
	 * @throws SimbNaoAlfabetoException
	 */
	public void printAFN() throws SimbNaoAlfabetoException {
		System.out.print("Estado iniciao: "+getInicio()+"\n");
		funcao.printFuncaoTransicao();
	}
	
	private boolean igualAlfabeto(AutomatroFN b) {
		return this.getAlfabeto() == b.getAlfabeto();
	}
	
}
