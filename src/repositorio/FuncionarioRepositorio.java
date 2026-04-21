package repositorio;

import modelo.Funcionario;

import java.util.LinkedHashSet;

public class FuncionarioRepositorio extends GenericoRepositorio<Funcionario> {

    public FuncionarioRepositorio() {
        super(new LinkedHashSet<Funcionario>());
    }

    public void aumentaSalarioFuncionarios(final Float porcentagem) {
        super.db.forEach(funcionario -> funcionario.aumentaSalario(porcentagem));

    }
}
