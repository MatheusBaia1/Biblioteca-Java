package com.matheus.biblioteca.service;
import com.matheus.biblioteca.model.Emprestimo;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.EmprestimoRepository;
import com.matheus.biblioteca.repository.LivroRepository;
import com.matheus.biblioteca.repository.Repositorio;
import com.matheus.biblioteca.repository.UsuarioRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioService {
    private LivroRepository livroRepositorio;
    private UsuarioRepository usuarioRepositorio;
    private EmprestimoRepository emprestimoRepositorio;

    public RelatorioService(LivroRepository livroRepositorio,
                            UsuarioRepository usuarioRepositorio,
                            EmprestimoRepository emprestimoRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.emprestimoRepositorio = emprestimoRepositorio;
    }
    public List<Livro> listarDisponiveis() throws SQLException, IOException {
        return livroRepositorio.listarTodos().stream()
                .filter(livro -> livro.isDisponivel())
                .collect(Collectors.toList());
    }
    public List<Emprestimo> historicoDoUsuario(String email) throws SQLException, IOException {
        return emprestimoRepositorio.listarTodos().stream()
                .filter(emprestimo -> emprestimo.getUsuario().getEmail().equals(email))
                .collect(Collectors.toList());
    }
    public List<Emprestimo> emprestimoNaoDevolvido() throws SQLException, IOException {
        return emprestimoRepositorio.listarTodos().stream()
                .filter(emprestimo -> emprestimo.getDataDevolucaoReal() == null)
                .collect(Collectors.toList());
    }
    public Map<Livro, Long> contarEmprestimosPorLivro () throws SQLException, IOException {
        return emprestimoRepositorio.listarTodos().stream()
                .collect(Collectors.groupingBy(emprestimo -> emprestimo.getLivro(), Collectors.counting()));
    }
    public Livro livroMaisEmprestado() throws SQLException, IOException {
        Map<Livro, Long> contagem = contarEmprestimosPorLivro();

        return contagem.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
