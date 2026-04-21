package modelo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = Objects.requireNonNull(salario);
        this.funcao = Objects.requireNonNull(funcao);
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public String salarioToString() {
        var nf = NumberFormat.getInstance(Locale.of("pt","BR"));
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(salario);
    }

    public void aumentaSalario(final Float porcentagem) {
        if (porcentagem < 0) {
            throw new IllegalArgumentException("Porcentagem inválida");
        }
        this.salario = salario.add(salario.multiply(BigDecimal.valueOf(porcentagem/100)));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(salario, that.salario) && Objects.equals(funcao, that.funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salario, funcao);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", super.toString(), salarioToString(), funcao);
    }
}
