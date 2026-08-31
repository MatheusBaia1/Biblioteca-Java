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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmprestimoService {

    private LivroRepository livroRepositorio;
    private UsuarioRepository usuarioRepositorio;
    private EmprestimoRepository emprestimoRepositorio;

    public EmprestimoService(LivroRepository livroRepositorio,
                             UsuarioRepository usuarioRepositorio,
                             EmprestimoRepository emprestimoRepositorio) {

        this.livroRepositorio = livroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.emprestimoRepositorio = emprestimoRepositorio;
    }
    public void emprestar (String isbn, String email) throws SQLException, IOException {
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
    public void devolver (String isbn) throws SQLException, IOException{
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