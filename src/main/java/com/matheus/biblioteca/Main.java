package com.matheus.biblioteca;
import com.matheus.biblioteca.model.Emprestimo;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.Repositorio;
import com.matheus.biblioteca.service.EmprestimoService;

public class Main {
    public static void main(String[] args){
        Repositorio<Livro> livroRepositorio = new Repositorio<>();
        Repositorio<Usuario> usuarioRepositorio = new Repositorio<>();
        Repositorio<Emprestimo> emprestimoRepositorio = new Repositorio<>();

        EmprestimoService service = new EmprestimoService(livroRepositorio, usuarioRepositorio, emprestimoRepositorio);

        livroRepositorio.salvar(new Livro("harry potter e a pedra filosofal", "J. K. Rowling", "001"));
        livroRepositorio.salvar(new Livro("O poder do subconsciente", "Joseph Murphy", "002"));
        usuarioRepositorio.salvar(new Usuario("Matheus baia", "Matheusbaia@gmail.com"));

        service.emprestar("001","Matheusbaia@gmail.com");
        System.out.println("Emprestimo realizado com sucesso!");

        try {
            service.emprestar("001", "Matheusbaia@gmail.com"); // deve falhar, livro já emprestado
        } catch (RuntimeException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }
        service.devolver("001");
        System.out.println("Livro devolvido com sucesso!");

        livroRepositorio.listarTodos().forEach(System.out::println);
        emprestimoRepositorio.listarTodos().forEach(System.out::println);

    }
}