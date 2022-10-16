package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VetorTest {

	private Aluno aluno1 = new Aluno("Joao");
	private Aluno aluno2 = new Aluno("Zeca");
	private Aluno aluno3 = new Aluno("zé");

	private Vetor vetor = new Vetor();

	@Test
	void adicionaPosicao() {
		vetor.adiciona(aluno1);
		vetor.adiciona(aluno2);
		vetor.adiciona(aluno1);
		vetor.adiciona(aluno2);
		vetor.adiciona(1, aluno3);
		for (int i = 0; i < 300; i++) {
			vetor.adiciona(aluno1);
		}
		Assertions.assertEquals(400, vetor.get().length);
		Assertions.assertEquals(vetor.pega(1), aluno3);

	}

	@Test
	void pega() throws IllegalArgumentException {
		vetor.adiciona(aluno1);
		vetor.adiciona(aluno2);
		Assertions.assertEquals(vetor.pega(0), aluno1);
	}

	@Test
	void remove() {
		vetor.adiciona(aluno1);
		vetor.adiciona(aluno2);
		vetor.adiciona(aluno3);
		vetor.remove(aluno2);
		Assertions.assertFalse(vetor.contem(aluno2));
	}

	@Test
	void contem() {
		vetor.adiciona(aluno1);
		vetor.adiciona(aluno2);
		Assertions.assertTrue(vetor.contem(aluno2));
	}

}
