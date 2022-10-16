package com.company;

import java.util.Arrays;

public class Vetor {

	private Aluno[] alunos = new Aluno[100];
	private int totalDeAlunos = 0;

	public void garanteEspaco() {
		if (totalDeAlunos == alunos.length) {
			Aluno[] novaArray = new Aluno[alunos.length * 2];
			for (int i = 0; i < totalDeAlunos; i++) {
				novaArray[i] = alunos[i];
			}
			this.alunos = novaArray;
		}
	}

	public void adiciona(Aluno aluno) {
		this.garanteEspaco();
		this.alunos[totalDeAlunos] = aluno;
		totalDeAlunos++;
	}

	public void adiciona(int posicao, Aluno aluno) {
		this.posicaoValida(posicao);
		int i = posicao;
		while (i <= totalDeAlunos) {
			alunos[i + 1] = alunos[i];
			i++;
		}
		alunos[posicao] = aluno;
	}

	public void posicaoValida(int posicao) {
		if (alunos[posicao] == null) {
			throw new IllegalArgumentException("Posicao inválida");
		}
	}

	public Aluno pega(int posicao) {
		this.posicaoValida(posicao);
		return alunos[posicao];
	}

	public void remove(Aluno aluno) {
		int posicao = 0;
		for (int i = 0; i < totalDeAlunos; i++) {
			if (alunos[i].equals(aluno))
				posicao = i + 1;
		}
		while (totalDeAlunos >= posicao) {
			alunos[posicao - 1] = alunos[posicao];
			posicao++;
		}
		totalDeAlunos--;
	}

	public Boolean contem(Aluno aluno) {
		for (int i = 0; i < totalDeAlunos; i++) {
			if (alunos[i].equals(aluno))
				return true;
		}
		return false;
	}

	public int tamanho() {
		return totalDeAlunos;
	}

	public Aluno[] get() {
		return alunos;
	}

	@Override
	public String toString() {
		return "Vetor{" + "alunos=" + Arrays.toString(alunos) + '}';
	}

}
