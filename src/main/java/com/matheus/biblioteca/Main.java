package com.matheus.biblioteca;

import com.matheus.biblioteca.model.Emprestimo;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.*;

import java.sql.SQLException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            LivroRepository livroRepository = new LivroRepository();
            UsuarioRepository usuarioRepository = new UsuarioRepository();
            EmprestimoRepository emprestimoRepository = new EmprestimoRepository(livroRepository, usuarioRepository);

            Livro livro = livroRepository.listarTodos().stream()
                    .filter(l -> l.getIsbn().equals("333"))
                    .findFirst()
                    .orElse(null);

            Usuario usuario = usuarioRepository.listarTodos().stream()
                    .filter(u -> u.getEmail().equals("ana@email.com"))
                    .findFirst()
                    .orElse(null);

            Emprestimo emprestimo = new Emprestimo(livro, usuario);
            emprestimoRepository.salvar(emprestimo);
            System.out.println("Empréstimo salvo no banco!");

            emprestimoRepository.listarTodos().forEach(System.out::println);
        } catch (SQLException | IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}