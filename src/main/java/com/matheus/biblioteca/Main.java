package com.matheus.biblioteca;

import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.EmprestimoRepository;
import com.matheus.biblioteca.repository.LivroRepository;
import com.matheus.biblioteca.repository.UsuarioRepository;
import com.matheus.biblioteca.service.EmprestimoService;
import com.matheus.biblioteca.service.RelatorioService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        LivroRepository livroRepository = new LivroRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        EmprestimoRepository emprestimoRepository = new EmprestimoRepository(livroRepository, usuarioRepository);
        EmprestimoService service = new EmprestimoService(livroRepository, usuarioRepository, emprestimoRepository);
        RelatorioService relatorioService = new RelatorioService(livroRepository, usuarioRepository, emprestimoRepository);

        int opcao;
        do {
            System.out.println("=======MENU=======");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("3 - Emprestar livro");
            System.out.println("4 - Devolver livro");
            System.out.println("5 - Listar livros disponíveis");
            System.out.println("6 - Ver histórico de um usuário");
            System.out.println("7 - Ver empréstimos não devolvidos");
            System.out.println("8 - Ver livro mais emprestado");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    String titulo = scanner.nextLine();

                    System.out.println("Digite o autor:");
                    String autor = scanner.nextLine();

                    System.out.println("Digite o ISBN:");
                    String isbn = scanner.nextLine();

                    livroRepository.salvar(new Livro(titulo, autor, isbn));
                    System.out.println("Livro cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite o nome do usuário:");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o email do usuário:");
                    String email = scanner.nextLine();

                    usuarioRepository.salvar(new Usuario(nome, email));
                    System.out.println("Usuario cadastrado com sucesso!");
                    break;
                case 3:
                    System.out.println("Digite o ISBN do livro:");
                    String isbnEmprestimo = scanner.nextLine();

                    System.out.println("Digite o email do usuário:");
                    String emailEmprestimo = scanner.nextLine();

                    try {
                        service.emprestar(isbnEmprestimo, emailEmprestimo);
                        System.out.println("Emprestimo cadastrado com sucesso!");
                    } catch (RuntimeException e) {
                        System.out.println("Erro " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Digite o ISBN do livro que deseja devolver:");
                    String devolver = scanner.nextLine();

                    try {
                        service.devolver(devolver);
                        System.out.println("Livro devolvido com sucesso!");
                    } catch (RuntimeException e) {
                        System.out.println("Erro " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Livros Disponiveis:");
                    relatorioService.listarDisponiveis().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Digite seu email:");
                    String emailDigitado = scanner.nextLine();

                    relatorioService.historicoDoUsuario(emailDigitado).forEach(System.out::println);
                    break;
                case 7:
                    System.out.println("Emprestimos não devolvidos:");
                    relatorioService.emprestimoNaoDevolvido().forEach(System.out::println);
                    break;
                case 8:
                    System.out.println("Livro mais emprestado:" + relatorioService.livroMaisEmprestado());
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }
}