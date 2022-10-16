package br.com.cwi.crescer.revisao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Gestor extends Funcionario{
    private int subordinado;
    public Gestor(String nome, LocalDate dataNascimento, TipoContrato tipoContrato, BigDecimal salarioBase,  int subordinado) {
        super( nome, dataNascimento, tipoContrato, salarioBase);
        this.subordinado=subordinado;
    }
    public String getFuncao() {
        return "Gestor";
    }
    public BigDecimal getSalarioLiquido(){
       return BigDecimal.valueOf(100*subordinado).add(super.getSalarioLiquido() ) ;
    }
}
