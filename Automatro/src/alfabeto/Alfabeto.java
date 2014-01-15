package alfabeto;

import java.util.Arrays;

public class Alfabeto {
	private int tamAlf;
	private char[] simbolos;
	
	public Alfabeto(char[] alfabeto) {
		tamAlf = alfabeto.length + 1;
		simbolos = new char[tamAlf];
		inserirSimbolos(alfabeto);
	}
	
	public int getTamAlf() {
		return simbolos.length;
	}
	public char[] getSimbolos() {
		return simbolos;
	}

	private void inserirSimbolos(char[] alf) {
		for (int i = 0; i < alf.length; i++) {
			simbolos[i] = alf[i];
		}
		simbolos[tamAlf-1] = 'E';
		Arrays.sort(simbolos);
	}
	
	public int getChave(char c) throws SimbNaoAlfabetoException {
		int index = Arrays.binarySearch(simbolos, c);
		if(index < 0)
			throw new SimbNaoAlfabetoException();
		return index;
	}
	
	public char getSimbolo(int chave) {
		assert(chave >= 0);
		assert(chave < simbolos.length);
		return simbolos[chave];
	}
	
	public boolean isAlfabeto(char simbolo) {
		for (int i = 0; i < getTamAlf(); i++) {
			if (simbolo == simbolos[i])
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(simbolos);
		result = prime * result + tamAlf;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alfabeto other = (Alfabeto) obj;
		if (!Arrays.equals(simbolos, other.simbolos))
			return false;
		if (tamAlf != other.tamAlf)
			return false;
		return true;
	} 

}
