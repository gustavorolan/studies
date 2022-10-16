package com.example.listaligada;

public class ListaLigada {
	private Celula primeira = null;
	private Celula ultima = null;
	private int totalDeElementos = 0;

	public void adicionaNoComeco(Object elemento){
		Celula nova = new Celula (elemento, primeira);
		primeira = nova;

		if(totalDeElementos==0){
			ultima = primeira;
		}

		totalDeElementos++;
	}
	public void adiciona(Object elemento){

		if (totalDeElementos==0) adicionaNoComeco(elemento);

		Celula nova = new Celula(elemento, null);

		ultima.setProximo(nova);
		ultima = nova;
		totalDeElementos++;
	}
	public void adiciona(int posicao, Object elemento){
		if(posicao==0){
			adicionaNoComeco(elemento);
		}
		else if(posicao==totalDeElementos){
			adiciona(elemento);
		}
		else {
		Celula anterior = this.pegaCelula(posicao-1);
		Celula nova = new Celula(elemento, anterior.getProximo());
		anterior.setProximo(nova);
		totalDeElementos++;
		}
	}
	public Object pega(int posicao){
		return pegaCelula(posicao).getElemento();
	}

	public Object tamanho(){
		return totalDeElementos;
	}

	public void removeDoComeco(){
		if (totalDeElementos==0) throw new IllegalArgumentException("Não elementos para remover");
		primeira= primeira.getProximo();
		totalDeElementos--;
		if ((totalDeElementos==0)) ultima=null;
	}

	private boolean posicaoOcupada(int posicao){
		return posicao>=0 && posicao<totalDeElementos;
	}

	private Celula pegaCelula(int posicao){

		if (!posicaoOcupada(posicao)) throw  new IllegalArgumentException("Posicao inexistente");

		Celula atual = primeira;

		for (int i = 0; i < posicao ; i++) {
			atual = atual.getProximo();
		}

		return atual;
	}

	@Override
	public String toString() {
		if (this.totalDeElementos==0) return "[]";

		Celula atual = primeira;

		StringBuilder builder = new StringBuilder("[");

		for (int i = 0; i < totalDeElementos ; i++) {
			builder.append(atual.getElemento());
			if(atual.getProximo()!=null) builder.append(",");
			atual = atual.getProximo();
		}
		builder.append("]");

		return builder.toString();
	}
}
