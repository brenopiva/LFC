package lfc.com.br.estruturas.de.dados;

import javax.crypto.spec.DESedeKeySpec;

public class Teste {
	public static void main(String args[]) {
		PilhaEnc pilha = new PilhaEnc();
		String expressao = "A-B+C";
		String expressaoSaida = "";
		for(int i = 0; i < expressao.length(); i++){
			String caracter = expressao.substring(i, i+1);
			if(isLetra(caracter)){
				expressaoSaida = expressaoSaida+caracter;
			}
			else if (caracter.equals("(")){
				pilha.empilhar(caracter);
			}
			else if (caracter.equals(")")){
				while(!String.valueOf(pilha.topo()).equals("(")){
					expressaoSaida = expressaoSaida+String.valueOf(pilha.topo());
					pilha.desempilhar();
				}
				pilha.desempilhar();
			}
			else if(isSimbolo(caracter)){
				while(!pilha.vazia() && temMaioPrioridade(String.valueOf(pilha.topo()), caracter)){
					if(isSimbolo(String.valueOf(pilha.topo()))){
						expressaoSaida = expressaoSaida+String.valueOf(pilha.topo());
						pilha.desempilhar();
					}else{
						break;
					}
				}
				pilha.empilhar(caracter);
			}
		}
		for(int i = 0; i <= pilha.tamanho(); i++){
			if(isSimbolo(String.valueOf(pilha.topo()))){
				expressaoSaida = expressaoSaida+pilha.topo();
			}
			pilha.desempilhar();
		}
		System.out.println(expressaoSaida);
	}
	
	public static boolean isLetra(String s){
		if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("(") || s.equals(")")){
			return false;
		}
		return true;
	}
	
	public static boolean isSimbolo(String s){
		if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
			return true;
		}
		return false;
	}
	
	public static boolean temMaioPrioridade(String p1, String p2){
		int prioridade1;
		int prioridade2;
		
		if(p1.equals("(") || p1.equals(")")){
			prioridade1 = 3;
		}
		else if(p1.equals("*") || p1.equals("/")){
			prioridade1 = 2;
		}
		else{
			prioridade1 = 1;
		}
		
		if(p2.equals("(") || p2.equals(")")){
			prioridade2 = 3;
		}		
		else if(p2.equals("*") || p2.equals("/")){
			prioridade2 = 2;
		}
		else{
			prioridade2 = 1;
		}
		
		return (prioridade1 >= prioridade2);
	}
}
