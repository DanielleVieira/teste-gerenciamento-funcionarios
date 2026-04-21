import modelo.Funcionario;
import repositorio.FuncionarioRepositorio;
import servico.FuncionarioServico;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Locale;

public class Main {
    private static final FuncionarioServico servico = new FuncionarioServico(new FuncionarioRepositorio());

    public static void main(String[] args) {
        System.out.println("3.1 Adicionando funcionários ...");
        servico.adicionaFuncionarios();
        System.out.println();

        System.out.println("3.2 Removendo funcionário João ...");
        servico.removeFuncionarioPorNome("João");
        System.out.println();

        System.out.println("3.3 Funcionários:");
        imprimeFuncionarios(servico.listaTodosFuncionarios());
        System.out.println();

        System.out.println("3.4 Aumentando o salário dos funcionários ...");
        servico.aumentaSalarioFuncionariosEmPorcentagem(10.0f);
        System.out.println();

        System.out.println("3.5 Agrupando funcionários por função ...");
        var funcionariosAgrupados  = servico.agrupaFuncionariosPorFuncao();
        System.out.println();

        System.out.println("3.6 Funcionários por função:");
        funcionariosAgrupados.forEach((grupo, funcionarios) -> System.out.println(grupo + " - " + funcionarios));
        System.out.println();

        System.out.println("3.8 Funcionários que fazem aniversário no mês 10 e 12:");
        imprimeFuncionarios(servico.listaFuncionariosQueFazemAniversarioNoMes(10));
        imprimeFuncionarios(servico.listaFuncionariosQueFazemAniversarioNoMes(12));
        System.out.println();

        System.out.println("3.9 Funcionário com maior idade:");
        var funcionario = servico.getFuncionarioMaiorIdade();
        System.out.println(funcionario.getNome() + " - " + Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears() + " anos");
        System.out.println();

        System.out.println("3.10 Funcionários em ordem alfabética:");
        imprimeFuncionarios(servico.ordenaFuncionariosPorNome());
        System.out.println();

        System.out.println("3.11 Total dos salários dos funcionários:");
        var nf = NumberFormat.getInstance(Locale.of("pt", "BR"));
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        System.out.println("Total: R$ " + nf.format(servico.salarioTotalFuncionarios()));
        System.out.println();

        System.out.println("3.12 Salários mínimos por funcionário:");
        servico.salariosMinimosPorFuncionario().forEach(System.out::println);
        System.out.println();
    }

    private static void imprimeFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.forEach(System.out::println);
    }
}
