package com.matheus.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;

public class Repositorio<T> {
    private List<T> dados = new ArrayList<>();

    public void salvar(T t) {
        dados.add(t);
    }
    public List<T> listarTodos() {
        return dados;
    }
    public void remover(T t) {
        dados.remove(t);
    }
}
