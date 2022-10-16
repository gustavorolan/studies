package br.com.cwi.crescer.revisao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tecnico extends Funcionario {
    public Tecnico(String nome, LocalDate dataNascimento, TipoContrato tipoContrato, BigDecimal salarioBase) {
        super( nome, dataNascimento, tipoContrato, salarioBase);
    }
    public String getFuncao() {
        return "Tecnico";
    }
}
