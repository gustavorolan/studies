import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilaTest {

	@Test
	void adiciona() {
		String expected = "Fila{alunos=[aluno1, aluno2, aluno3, aluno4]}";

		Fila fila = new Fila();

		String aluno1 = "aluno1";
		String aluno2 = "aluno2";
		String aluno3 = "aluno3";
		String aluno4 = "aluno4";

		fila.adiciona(aluno1);
		fila.adiciona(aluno2);
		fila.adiciona(aluno3);
		fila.adiciona(aluno4);

		Assertions.assertEquals(expected,fila.toString());

	}

	@Test
	void remove() {
		String expected = "Fila{alunos=[aluno2, aluno3, aluno4]}";

		Fila fila = new Fila();

		String aluno1 = "aluno1";
		String aluno2 = "aluno2";
		String aluno3 = "aluno3";
		String aluno4 = "aluno4";

		fila.adiciona(aluno1);
		fila.adiciona(aluno2);
		fila.adiciona(aluno3);
		fila.adiciona(aluno4);
		fila.remove();

		Assertions.assertEquals(expected,fila.toString());

	}
}