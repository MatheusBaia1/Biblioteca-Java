package com.matheus.biblioteca.service;
import com.matheus.biblioteca.model.Emprestimo;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.Repositorio;

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
}
