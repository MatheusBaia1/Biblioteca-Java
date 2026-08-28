package com.matheus.biblioteca.repository;

public class EmprestimoRepository {
    private LivroRepository livroRepository;
    private UsuarioRepository usuarioRepository;

    public EmprestimoRepository(LivroRepository livroRepository, UsuarioRepository usuarioRepository) {
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }
}
