package com.example.pilha;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PilhaTest {

	@Test
	void insere() {
		String expected = "Pilha{nomes=[Mauricio, gustavo]}";

		Pilha pilha = new Pilha();
		String mauricio = "Mauricio";
		String gustavo = "gustavo";

		pilha.insere(mauricio);
		pilha.insere(gustavo);

		Assertions.assertEquals(expected,pilha.toString());
	}

	@Test
	void remove() {
		String expected = "Pilha{nomes=[Mauricio, gustavo, Mauricio, gustavo, Mauricio]}";

		Pilha pilha = new Pilha();
		String mauricio = "Mauricio";
		String gustavo = "gustavo";

		pilha.insere(mauricio);
		pilha.insere(gustavo);
		pilha.insere(mauricio);
		pilha.insere(gustavo);
		pilha.insere(mauricio);
		pilha.insere(gustavo);

		pilha.remove();

		Assertions.assertEquals(expected,pilha.toString());
	}

	@Test
	void vazia() {
		Pilha pilha = new Pilha();

		boolean vazia = pilha.vazia();

		Assertions.assertTrue(vazia);
	}

	@Test
	void naoEstaVazia() {
		Pilha pilha = new Pilha();

		String mauricio = "Mauricio";
		String gustavo = "gustavo";

		pilha.insere(mauricio);
		pilha.insere(gustavo);

		boolean vazia = pilha.vazia();

		Assertions.assertFalse(vazia);
	}
}