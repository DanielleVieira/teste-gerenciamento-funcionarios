package modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Pessoa {
    private final String nome;
    private final LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = Objects.requireNonNull(nome);
        this.dataNascimento = Objects.requireNonNull(dataNascimento);
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String dataNascimentoToString() {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNascimento.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(nome, pessoa.nome) && Objects.equals(dataNascimento, pessoa.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataNascimento);
    }

    @Override
    public String toString() {
        return String.format("%s | %s", nome, dataNascimentoToString());
    }
}
