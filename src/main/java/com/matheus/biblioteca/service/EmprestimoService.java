package com.matheus.biblioteca.service;
import com.matheus.biblioteca.model.Emprestimo;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.Repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmprestimoService {

    private Repositorio<Livro> livroRepositorio;
    private Repositorio<Usuario> usuarioRepositorio;
    private Repositorio<Emprestimo> emprestimoRepositorio;

    public EmprestimoService(Repositorio<Livro> livroRepositorio,
                             Repositorio<Usuario> usuarioRepositorio,
                             Repositorio<Emprestimo> emprestimoRepositorio) {

        this.livroRepositorio = livroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.emprestimoRepositorio = emprestimoRepositorio;
    }
    public void emprestar (String isbn, String email) {
        Optional<Livro> livroEncontrado = livroRepositorio.listarTodos().stream()
                .filter(livro -> livro.getIsbn().equals(isbn))
                .findFirst();
        if (!livroEncontrado.isPresent()) {
            throw new RuntimeException("Livro não encontrado");
        }
        Optional<Usuario> usuarioEncontrado = usuarioRepositorio.listarTodos().stream()
                .filter(usuario -> usuario.getEmail().equals(email))
                .findFirst();
        if (!usuarioEncontrado.isPresent()) {
            throw new RuntimeException("Usuario não encontrado");
        }
        Livro livro = livroEncontrado.get();
        if (!livro.isDisponivel()) {
            throw new RuntimeException("Livro já está emprestado");
        }
        Usuario usuario = usuarioEncontrado.get();
        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        livro.setDisponivel(false);
        emprestimoRepositorio.salvar(emprestimo);
    }
    public void devolver (String isbn) {
        Optional<Emprestimo> emprestimoEncontrado = emprestimoRepositorio.listarTodos().stream()
                .filter(emprestimo -> emprestimo.getLivro().getIsbn().equals(isbn) && emprestimo.getDataDevolucaoReal() == null)
                .findFirst();
        if (!emprestimoEncontrado.isPresent()) {
            throw new RuntimeException("Nenhum emprestimo ativo encontrado para esse livro");
        }
        Emprestimo emprestimo = emprestimoEncontrado.get();
        emprestimo.setDataDevolucaoReal(LocalDate.now());
        emprestimo.getLivro().setDisponivel(true);
    }
}