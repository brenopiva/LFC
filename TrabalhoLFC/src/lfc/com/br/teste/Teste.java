package lfc.com.br.teste;

import java.util.ArrayList;

import lfc.com.br.AFD;
import lfc.com.br.Estado;
import lfc.com.br.Transicao;

public class Teste {
	public static void main(String args[]) {
		Estado e1 = new Estado(false,1);
		Estado e2 = new Estado(false,2);
		Estado e3 = new Estado(false,3);
		Estado e4 = new Estado(true,4);
		
		Transicao t1 = new Transicao(e2, "0");
		Transicao t12 = new Transicao(e1, "1");
		e1.addTransicao(t1);
		e1.addTransicao(t12);
		
		Transicao t2 = new Transicao(e3, "0");
		Transicao t22 = new Transicao(e3, "1");
		e2.addTransicao(t2);
		e2.addTransicao(t22);
		
		Transicao t3 = new Transicao(e4, "0");
		Transicao t33 = new Transicao(e4, "1");
		e3.addTransicao(t3);
		e3.addTransicao(t33);
		
		Transicao t4 = new Transicao(e4, "0");
		Transicao t5 = new Transicao(e4, "1");
		e4.addTransicao(t4);
		e4.addTransicao(t5);
		
		ArrayList<Estado> estados = new ArrayList<Estado>();
		estados.add(e1);
		estados.add(e2);
		estados.add(e3);
		estados.add(e4);
		AFD afd = new AFD(estados);
		System.out.println(afd.verificarPalavra("1000"));
	}
}
