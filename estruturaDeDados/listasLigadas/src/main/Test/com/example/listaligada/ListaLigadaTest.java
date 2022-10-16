package com.example.listaligada;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ListaLigadaTest {

	@Test
	void adicionaNoComeco() {

		 String expected = "[paulo,zeca,mauricio]";

		 ListaLigada listaLigada= new ListaLigada();

		 listaLigada.adicionaNoComeco("mauricio");
		 listaLigada.adicionaNoComeco("zeca");
		 listaLigada.adicionaNoComeco("paulo");

		 Assertions.assertEquals(expected,listaLigada.toString());
	}

	@Test
	void adiciona() {

		String expected = "[paulo,zeca,mauricio,marcelo,mateus,olavo]";

		ListaLigada listaLigada= new ListaLigada();
		listaLigada.adicionaNoComeco("mauricio");
		listaLigada.adicionaNoComeco("zeca");
		listaLigada.adicionaNoComeco("paulo");

		listaLigada.adiciona("marcelo");
		listaLigada.adiciona("mateus");
		listaLigada.adiciona("olavo");

		Assertions.assertEquals(expected,listaLigada.toString());

	}

	@Test
	void testAdiciona() {
		String expected = "[paulo,zeca,tiago,mauricio,marcelo,mateus,olavo]";

		ListaLigada listaLigada= new ListaLigada();
		listaLigada.adicionaNoComeco("mauricio");
		listaLigada.adicionaNoComeco("zeca");
		listaLigada.adicionaNoComeco("paulo");

		listaLigada.adiciona("marcelo");
		listaLigada.adiciona("mateus");
		listaLigada.adiciona("olavo");

		listaLigada.adiciona(2,"tiago");

		Assertions.assertEquals(expected,listaLigada.toString());
	}

	@Test
	void pega() {
		String expected = "tiago";

		ListaLigada listaLigada= new ListaLigada();
		listaLigada.adicionaNoComeco("mauricio");
		listaLigada.adicionaNoComeco("zeca");
		listaLigada.adicionaNoComeco("paulo");

		listaLigada.adiciona("marcelo");
		listaLigada.adiciona("mateus");
		listaLigada.adiciona("olavo");

		listaLigada.adiciona(2,"tiago");

		Assertions.assertEquals(expected,listaLigada.pega(2).toString());
	}

	@Test
	void tamanho() {
		int expected = 7;

		ListaLigada listaLigada= new ListaLigada();
		listaLigada.adicionaNoComeco("mauricio");
		listaLigada.adicionaNoComeco("zeca");
		listaLigada.adicionaNoComeco("paulo");

		listaLigada.adiciona("marcelo");
		listaLigada.adiciona("mateus");
		listaLigada.adiciona("olavo");

		listaLigada.adiciona(2,"tiago");

		Assertions.assertEquals(expected, listaLigada.tamanho());
	}

	@Test
	void removeDoComeco() {
		String expected = "[zeca,tiago,mauricio,marcelo,mateus,olavo]";

		ListaLigada listaLigada= new ListaLigada();
		listaLigada.adicionaNoComeco("mauricio");
		listaLigada.adicionaNoComeco("zeca");
		listaLigada.adicionaNoComeco("paulo");

		listaLigada.adiciona("marcelo");
		listaLigada.adiciona("mateus");
		listaLigada.adiciona("olavo");

		listaLigada.adiciona(2,"tiago");

		listaLigada.removeDoComeco();

		Assertions.assertEquals(expected, listaLigada.toString());
	}
}