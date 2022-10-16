package com.example.pilha;

import java.util.LinkedList;
import java.util.List;

public class Pilha {

	private List<String> nomes	 = new LinkedList<String>();

	public void insere(String nome) {
		nomes.add(nome);
	}

	public void remove ( ){
		nomes.remove(nomes.size()-1);
	}
	public  boolean vazia( ){
		return nomes.isEmpty();
	}

	@Override
	public String toString() {
		return "Pilha{" + "nomes=" + nomes + '}';
	}
}
