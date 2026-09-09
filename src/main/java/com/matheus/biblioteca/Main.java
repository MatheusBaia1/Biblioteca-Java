package com.matheus.biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("=======MENU=======");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Cadastrar usuario");
            System.out.println("3 - Emprestar livro");
            System.out.println("4 - Devolver livro");
            System.out.println("5 - Listar livros disponíveis");
            System.out.println("6 - Ver histórico de um usuário");
            System.out.println("7 - Ver empréstimos não devolvidos");
            System.out.println("8 - Ver livro mais emprestado");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Você escolheu cadastrar livro");
                    break;
                case 2:
                    System.out.println("Você escolheu cadastrar usuário");
                    break;
                case 3:
                    System.out.println("Emprestar livro");
                    break;
                case 4:
                    System.out.println("Devolver livro");
                    break;
                case 5:
                    System.out.println("Listar livros disponiveis");
                    break;
                case 6:
                    System.out.println("Ver histórico de um usuário");
                    break;
                case 7:
                    System.out.println("Ver empréstimos não devolvidos");
                    break;
                case 8:
                    System.out.println("Ver livro mais emprestado");
                    break;
                case 0:
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }
}