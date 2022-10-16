package br.com.cwi.crescer.revisao;

import java.math.BigDecimal;
import java.util.List;

public interface FuncionarioService {
    List<Funcionario> filtrarComSalarioMenorQue(BigDecimal bigDecimal, List<Funcionario> funcionarios);
    List<String> retornarApenasPrimeiroNome(List<Funcionario> funcionarios);
    int somarIdade(List<Funcionario> funcionarios);
}
