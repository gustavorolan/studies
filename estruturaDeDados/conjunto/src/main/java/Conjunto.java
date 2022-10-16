import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

public class Conjunto {

	private ArrayList<LinkedList<String>> tabela = new ArrayList<>();

	public Conjunto(){
		for(int i=0;i<26;i++){
			tabela.add(new LinkedList<>());
		}
	}

	public void adiciona(String palavra){
		LinkedList<String> strings = retornaTabela(palavra);
		if (!contem(strings,palavra)) strings.add(palavra);
	}

	public void remove(String palavra){
		LinkedList<String> strings = retornaTabela(palavra);
		if (contem(strings,palavra)) strings.remove(palavra);
	}

	public boolean contem(LinkedList<String> strings,String palavra){
		return strings.contains(palavra);
	}

	private LinkedList<String> retornaTabela(String palavra){
		int indice = calculaIndiceDaTabela(palavra);
		return tabela.get(indice);
	}

	private int calculaIndiceDaTabela(String palavra) {
		return palavra.toLowerCase(Locale.ROOT).charAt(0)%26;
	}

	@Override
	public String toString() {
		return "Conjunto{" + "tabela=" + tabela + '}';
	}

}
