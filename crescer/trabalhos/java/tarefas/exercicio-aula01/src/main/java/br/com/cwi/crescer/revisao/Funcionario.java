package br.com.cwi.crescer.revisao;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public  class Funcionario {
    private String nome;
    private LocalDate dataNascimento ;
    private TipoContrato tipoContrato ;
    private BigDecimal salarioBase ;

    public Funcionario( String nome, LocalDate dataNascimento, TipoContrato tipoContrato, BigDecimal salarioBase) {

        this.nome=nome;
        this.dataNascimento=dataNascimento;
        this.tipoContrato=tipoContrato;
        this.salarioBase=salarioBase;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }


    public String getFuncao(){
        return null;
    };

    public void setSalarioBase(BigDecimal novoSalarioBase) {
        salarioBase=salarioBase.multiply(BigDecimal.valueOf(1.75));
    }

    public BigDecimal getSalarioLiquido() {
        if(tipoContrato.equals(tipoContrato.CLT)){
            return  salarioBase.multiply(BigDecimal.valueOf(0.7));
        }
        return  salarioBase.multiply(BigDecimal.valueOf(0.9));

    }
    public int getIdade() {
        return  (int) ChronoUnit.YEARS.between(dataNascimento,LocalDate.now()) ;
    }

}
