package com.matheus.biblioteca.service;
import com.matheus.biblioteca.model.Emprestimo;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.Repositorio;

import java.util.List;

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
}
