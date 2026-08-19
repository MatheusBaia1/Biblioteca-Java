package com.matheus.biblioteca.model;
import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    @Override
    public String toString() {
        String status = dataDevolucaoReal == null ? "Ativo" : "Devolvido em " + dataDevolucaoReal;
        return "==========================" + "\n" +
                "Emprestimo do livro: " + livro.getTitulo() + "\n" +
                "usuario: " + usuario.getNome() + " (" + livro.getAutor() + ")" + "\n" +
                "Data do emprestimo: " + dataEmprestimo + "\n" +
                "Status: " + status + "\n" +
                "--------------------------";
    }

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = LocalDate.now().plusDays(30);
    }
    public Livro getLivro() {
        return livro;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }
    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
}
