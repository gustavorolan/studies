import java.util.LinkedList;
import java.util.List;

public class Fila {
	public List<String> alunos = new LinkedList<String>();

	public void adiciona (String aluno) {
		alunos.add(aluno);
	}

	public void remove () {
		alunos.remove(0);
	}

	@Override
	public String toString() {
		return "Fila{" + "alunos=" + alunos + '}';
	}
}
