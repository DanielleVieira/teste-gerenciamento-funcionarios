package repositorio;

import modelo.Pessoa;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class GenericoRepositorio<T extends Pessoa> {
    protected final Set<T> db;

    public GenericoRepositorio(Set<T> db) {
        this.db = db;
    }

    public boolean adiciona(final T dado) {
        Objects.requireNonNull(dado);
        return  db.add(dado);

    }

    @SafeVarargs
    public final boolean adicionaTodos(final T... dados) {
        Objects.requireNonNull(dados);
        var temNull = Arrays.stream(dados).anyMatch(Objects::isNull);
        if (temNull) {
            throw new NullPointerException();
        }
        return db.addAll(Arrays.asList(dados));
    }

    public boolean remove(final T dado) {
        Objects.requireNonNull(dado);
        return db.remove(dado);
    }

    public boolean removePor(final Predicate<T> condicao) {
        Objects.requireNonNull(condicao);
        return db.removeIf(condicao);
    }

    public List<T> listaTodos() {
        return db.stream().toList();
    }

    public List<T> filtra(Predicate<T> filtro) {
        Objects.requireNonNull(filtro);
        return db.stream().filter(filtro).toList();
    }
}
