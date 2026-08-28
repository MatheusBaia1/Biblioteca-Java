package com.matheus.biblioteca;
import com.matheus.biblioteca.model.Emprestimo;
import com.matheus.biblioteca.model.Livro;
import com.matheus.biblioteca.model.Usuario;
import com.matheus.biblioteca.repository.LivroRepository;
import com.matheus.biblioteca.repository.Repositorio;
import com.matheus.biblioteca.repository.UsuarioRepository;
import com.matheus.biblioteca.service.EmprestimoService;
import com.matheus.biblioteca.service.RelatorioService;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import com.matheus.biblioteca.repository.ConnectionFactory;

public class Main {
    public static void main(String[] args){
        /*Repositorio<Livro> livroRepositorio = new Repositorio<>();
        Repositorio<Usuario> usuarioRepositorio = new Repositorio<>();
        Repositorio<Emprestimo> emprestimoRepositorio = new Repositorio<>();

        EmprestimoService service = new EmprestimoService(livroRepositorio, usuarioRepositorio, emprestimoRepositorio);
        RelatorioService relatorioService = new RelatorioService(livroRepositorio, usuarioRepositorio, emprestimoRepositorio);

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

        service.emprestar("001", "Matheusbaia@gmail.com");
        service.devolver("001");

        service.emprestar("001", "Matheusbaia@gmail.com");
        service.devolver("001");

        service.emprestar("002", "Matheusbaia@gmail.com");
// deixa esse sem devolver, pra testar o relatório de "não devolvidos"

        System.out.println("Livros disponíveis:");
        relatorioService.listarDisponiveis().forEach(System.out::println);

        System.out.println("Histórico do usuário:");
        relatorioService.historicoDoUsuario("Matheusbaia@gmail.com").forEach(System.out::println);

        System.out.println("Empréstimos não devolvidos:");
        relatorioService.emprestimoNaoDevolvido().forEach(System.out::println);

        System.out.println("Livro mais emprestado:");
        System.out.println(relatorioService.livroMaisEmprestado());

        try {
            Connection conexao = ConnectionFactory.getConexao();
            System.out.println("Conectado com sucesso via ConnectionFactory!");
            conexao.close();
        } catch (SQLException | IOException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }*/
        try {
            LivroRepository livroRepository = new LivroRepository();
            //livroRepository.salvar(new Livro("O Hobbit", "J.R.R. Tolkien", "333"));
            //System.out.println("Livro salvo no banco com sucesso!");

            livroRepository.listarTodos().forEach(System.out::println);
        } catch (SQLException | IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
        try {
            UsuarioRepository usuarioRepository = new UsuarioRepository();
            usuarioRepository.salvar(new Usuario("Ana Silva", "ana@email.com"));
            usuarioRepository.listarTodos().forEach(System.out::println);
        } catch (SQLException | IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}