package br.com.cwi.crescer.revisao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevisaoTest {


    @Test
    @DisplayName("RQ01: Funcionário deve ter nome, data de nascimento, tipo de contrato e salário base")
    void requisito01() {

        String nome = "Joao da Silva";
        LocalDate dataNascimento = LocalDate.of(1991, 07, 23);
        TipoContrato tipoContrato = TipoContrato.CLT;
        BigDecimal salario = new BigDecimal("2000");

        Funcionario funcionario = new Funcionario(nome, dataNascimento, tipoContrato, salario);
        assertEquals(nome, funcionario.getNome());
        assertEquals(dataNascimento, funcionario.getDataNascimento());
        assertEquals(tipoContrato, funcionario.getTipoContrato());
        assertEquals(salario, funcionario.getSalarioBase());
    }


    @Test
    @DisplayName("RQ02: Funcionário pode ser administrativo, técnico ou gestor")
     void requisito02() {

        Funcionario administrativo = criarFuncionarioAdministrativo();
        Funcionario tecnico = criarFuncionarioTecnico();
        Funcionario gestor = criarFuncionarioGestor();

        assertEquals("Administrativo", administrativo.getFuncao());
        assertEquals("Tecnico", tecnico.getFuncao());
        assertEquals("Gestor", gestor.getFuncao());
    }



    @Test
    @DisplayName("RQ03: Funcionário pode ter salário base alterado")
    void requisito03() {

        Funcionario funcionario = criarFuncionarioAdministrativo();

        BigDecimal novoSalarioBase = new BigDecimal("3500");
        funcionario.setSalarioBase(novoSalarioBase);

        assertThat(funcionario.getSalarioBase(), comparesEqualTo(novoSalarioBase));
    }
    @Test
    @DisplayName("RQ04: Salário líquido do funcionário CLT é o salário base menos 30% de desconto")
    void requisito04() {
        Funcionario funcionario = criarFuncionarioAdministrativo();
        assertThat(funcionario.getSalarioLiquido(), comparesEqualTo(new BigDecimal("1400")));
    }

    @Test
    @DisplayName("RQ05: Salário líquido do funcionário PJ é o salário base menos 10% de desconto")
    void requisito05() {
        Funcionario funcionario = criarFuncionarioTecnico();
        assertThat(funcionario.getSalarioLiquido(), comparesEqualTo(new BigDecimal("2700")));
    }

 @Test
 @DisplayName("RQ06: Salário líquido do gestor é o salário base menos o desconto do tipo de contrato e mais um bonus de 100 reais por cada subordinado")
 void requisito06() {
     Funcionario funcionario = criarFuncionarioGestor();
     assertThat(funcionario.getSalarioLiquido(), comparesEqualTo(new BigDecimal("5500")));
 }

     private FuncionarioService funcionarioService = new FuncionarioServiceImpl();

     @Test
     @DisplayName("RQ07: Listar apenas os funcionários com salário líquido menor que 3000")
     void requisito07() {
         List<Funcionario> funcionarios = criarlistaComTodosOsFuncionarios();
         List<Funcionario> resultado = funcionarioService.filtrarComSalarioMenorQue(new BigDecimal("3000"), funcionarios);
         assertThat(resultado, hasSize(2));
         assertThat(resultado, contains(funcionarios.get(0), funcionarios.get(1)));
     }

    @Test
    @DisplayName("RQ08: Retornar apenas o primeiro nome dos funcionários")
    void requisito08() {
        List<Funcionario> funcionarios = criarlistaComTodosOsFuncionarios();
        List<String> resultado = funcionarioService.retornarApenasPrimeiroNome(funcionarios);
        assertThat(resultado, contains("Joao", "Maria", "Joaquim"));
     }


    @Test
    @DisplayName("RQ09: Somar idade em anos de todos os funcionários")
    void requisito09() {
        List<Funcionario> funcionarios = criarlistaComTodosOsFuncionarios();
        int resultado = funcionarioService.somarIdade(funcionarios);
        assertThat(resultado, is(90));

    }

    private Administrativo criarFuncionarioAdministrativo() {
        return new Administrativo("Joao da Silva",
                LocalDate.of(1991, 07, 23),
                TipoContrato.CLT,
                new BigDecimal("2000"));
    }

    private Tecnico criarFuncionarioTecnico() {
        return new Tecnico("Maria de Lima",
                LocalDate.of(1995, 01, 12),
                TipoContrato.PJ,
                new BigDecimal("3000"));
    }

    private Gestor criarFuncionarioGestor() {
        return new Gestor("Joaquim Rosa",
                LocalDate.of(1988, 05, 4),
                TipoContrato.PJ,
                new BigDecimal("5000"),
                10);
    }

    private List<Funcionario> criarlistaComTodosOsFuncionarios() {
        return Arrays.asList(criarFuncionarioAdministrativo(), criarFuncionarioTecnico(), criarFuncionarioGestor());
    }



}
