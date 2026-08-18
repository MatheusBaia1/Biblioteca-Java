package com.matheus.biblioteca;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.Repositorio;

public class Main {
    public static void main(String[] args){
        Repositorio<Livro> repositorioLivros = new Repositorio<>();
        Repositorio<Usuario> repositorioUsuarios = new Repositorio<>();

        repositorioLivros.salvar(new Livro("harry potter e a pedra filosofal", "J. K. Rowling", 001));
        repositorioLivros.salvar(new Livro("O poder do subconsciente", "Joseph Murphy", 002));
        repositorioUsuarios.salvar(new Usuario("Matheus baia", "Matheusbasia@Gmail.com"));

        repositorioLivros.listarTodos().forEach(System.out::println);
        repositorioUsuarios.listarTodos().forEach(System.out::println);
    }
}