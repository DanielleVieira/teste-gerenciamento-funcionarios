package servico;

import dataset.Dataset;
import modelo.Funcionario;
import modelo.Pessoa;
import repositorio.FuncionarioRepositorio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioServico {
    public final FuncionarioRepositorio repositorio;

    public FuncionarioServico(FuncionarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Map<String, List<Funcionario>> agrupaFuncionariosPorFuncao() {
        return repositorio
                .listaTodos()
                .stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public List<Funcionario> listaFuncionariosQueFazemAniversarioNoMes(int mes) {
        Objects.requireNonNull(mes);
        return repositorio.filtra(
                        funcionario -> funcionario.getDataNascimento().getMonthValue() == mes
                );
    }

    public List<Funcionario> listaTodosFuncionarios() {
        return repositorio.listaTodos();
    }

    public void removeFuncionarioPorNome(String nome) {
        Objects.requireNonNull(nome);
        repositorio.removePor(funcionario -> funcionario.getNome().equals(nome));
    }

    public void aumentaSalarioFuncionariosEmPorcentagem(Float porcentagem) {
        Objects.requireNonNull(porcentagem);
        repositorio.aumentaSalarioFuncionarios(porcentagem);
    }

    public void adicionaFuncionarios() {
        repositorio.adicionaTodos(Dataset.FUNCIONARIOS.toArray(Funcionario[]::new));
    }

    public Funcionario getFuncionarioMaiorIdade() {
        return repositorio
                .listaTodos()
                .stream()
                .max(Comparator.comparing(
                        funcionario -> Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears()
                ))
                .orElseThrow(() -> new NoSuchElementException("Nenhum funcionário encontrado"));
    }

    public BigDecimal salarioTotalFuncionarios() {
        return repositorio
                .listaTodos()
                .stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    public List<Funcionario> ordenaFuncionariosPorNome() {
        return repositorio
                .listaTodos()
                .stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .toList();
    }

    public List<String> salariosMinimosPorFuncionario() {
        var nf = NumberFormat.getInstance(Locale.of("pt", "BR"));
        var singular = " salário mínimo";
        var plural = " salários mínimos";

        return repositorio
                .listaTodos()
                .stream()
                .map(funcionario -> {
                            var quantidadeMinimos = funcionario.getSalario().divide(BigDecimal.valueOf(1212), 0, RoundingMode.FLOOR);
                            String complemento;
                            if(quantidadeMinimos.intValue() == 1) {
                                complemento = singular;
                            } else {
                                complemento = plural;
                            }
                            return funcionario.getNome() + ": " + nf.format(quantidadeMinimos) + complemento;
                        })
                .toList();
    }
}
