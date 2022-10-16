import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConjuntoTest {

	@Test
	void conjunto(){
		String expected ="Conjunto{tabela=[[], [], [], [], [], [matheus], " +
				"[], [], [], [], [], [], [], [], [], [], [], [], [], [], [" +
				"], [], [], [], [], [gustavo, guilherme]]}";

		Conjunto conjunto = new Conjunto();

		conjunto.adiciona("gustavo");
		conjunto.adiciona("guilherme");
		conjunto.adiciona("matheus");
		conjunto.adiciona("gustavo");

		Assertions.assertEquals(expected,conjunto.toString());
	}
	@Test
	void remove(){
		String expected ="Conjunto{tabela=[[], [], [], [], [], [], " +
				"[], [], [], [], [], [], [], [], [], [], [], [], [], [], [" +
				"], [], [], [], [], [gustavo, guilherme]]}";

		Conjunto conjunto = new Conjunto();

		conjunto.adiciona("gustavo");
		conjunto.adiciona("guilherme");
		conjunto.adiciona("matheus");
		conjunto.adiciona("gustavo");
		conjunto.remove("matheus");

		Assertions.assertEquals(expected,conjunto.toString());
	}

}