package br.com.cwi.crescer.revisao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Administrativo extends Funcionario{
    public Administrativo(String nome, LocalDate dataNascimento, TipoContrato tipoContrato, BigDecimal salarioBase) {
        super( nome, dataNascimento, tipoContrato, salarioBase);
    }
    public String getFuncao() {
        return "Administrativo";
    }
}
