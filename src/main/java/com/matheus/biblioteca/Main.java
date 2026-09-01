package com.matheus.biblioteca;

import com.matheus.biblioteca.repository.*;
import com.matheus.biblioteca.service.EmprestimoService;

import java.sql.SQLException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            LivroRepository livroRepository = new LivroRepository();
            UsuarioRepository usuarioRepository = new UsuarioRepository();
            EmprestimoRepository emprestimoRepository = new EmprestimoRepository(livroRepository, usuarioRepository);

            EmprestimoService service = new EmprestimoService(livroRepository, usuarioRepository, emprestimoRepository);

            service.emprestar("333", "ana@email.com");
            System.out.println("Emprestado com sucesso!");

            livroRepository.listarTodos().forEach(System.out::println);

            service.devolver("333");
            System.out.println("Devolvido com sucesso!");

            livroRepository.listarTodos().forEach(System.out::println);
            emprestimoRepository.listarTodos().forEach(System.out::println);
        } catch (SQLException | IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}