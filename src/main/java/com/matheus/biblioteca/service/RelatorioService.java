package com.matheus.biblioteca.service;
import com.matheus.biblioteca.model.Emprestimo;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.Repositorio;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioService {
    private Repositorio<Livro> livroRepositorio;
    private Repositorio<Usuario> usuarioRepositorio;
    private Repositorio<Emprestimo> emprestimoRepositorio;

    public RelatorioService(Repositorio<Livro> livroRepositorio,
                            Repositorio<Usuario> usuarioRepositorio,
                            Repositorio<Emprestimo> emprestimoRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.emprestimoRepositorio = emprestimoRepositorio;
    }
    public List<Livro> listarDisponiveis() {
        return livroRepositorio.listarTodos().stream()
                .filter(livro -> livro.isDisponivel())
                .collect(Collectors.toList());
    }
    public List<Emprestimo> historicoDoUsuario(String email) {
        return emprestimoRepositorio.listarTodos().stream()
                .filter(emprestimo -> emprestimo.getUsuario().getEmail().equals(email))
                .collect(Collectors.toList());
    }
    public List<Emprestimo> emprestimoNaoDevolvido() {
        return emprestimoRepositorio.listarTodos().stream()
                .filter(emprestimo -> emprestimo.getDataDevolucaoReal() == null)
                .collect(Collectors.toList());
    }
    public Map<Livro, Long> contarEmprestimosPorLivro () {
        return emprestimoRepositorio.listarTodos().stream()
                .collect(Collectors.groupingBy(emprestimo -> emprestimo.getLivro(), Collectors.counting()));
    }
    public Livro livroMaisEmprestado() {
        Map<Livro, Long> contagem = contarEmprestimosPorLivro();

        return contagem.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
