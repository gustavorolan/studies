package br.com.cwi.crescer.revisao;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioServiceImpl implements FuncionarioService {
    @Override
    public List<Funcionario> filtrarComSalarioMenorQue(BigDecimal bigDecimal, List<Funcionario> funcionarios) {
       return  funcionarios.stream().filter(funcionario -> funcionario.getSalarioLiquido().compareTo(bigDecimal)==-1).collect(Collectors.toList());
    }

    @Override
    public List<String> retornarApenasPrimeiroNome(List<Funcionario> funcionarios) {
        List<String[]> funcionariosSplited = funcionarios.stream().map(funcionario -> funcionario.getNome().split(" ")).collect(Collectors.toList());
        List<String> funcionariosPrimeiroNome = funcionariosSplited.stream().map(nome -> nome[0]).collect(Collectors.toList());
        return  funcionariosPrimeiroNome;
    }

    @Override
    public int somarIdade(List<Funcionario> funcionarios ) {
        List<Integer> idadeDosFuncionarios =funcionarios.stream().map(funcionario -> funcionario.getIdade()).collect(Collectors.toList());
        return  idadeDosFuncionarios.stream().reduce(0,(acc,idade)->acc+idade);
    }
}
