package com.company;

import java.util.Objects;

public class Aluno {

	private String nome;

	public Aluno(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null) {
			return false;
		} else if (!(o instanceof Aluno)) {
			return false;
		}
		Aluno aluno = (Aluno) o;
		return getNome().equals(aluno.getNome());
	}

	@Override
	public String toString() {
		return "Aluno{" + "nome='" + nome + '\'' + '}';
	}

}
