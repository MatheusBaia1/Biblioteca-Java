package com.matheus.biblioteca.model;

public class Livro {
    private Integer id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }
    public Integer getId() {
        return id;
    }
    public void setId (Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return ("==========================" + "\n" +
                "Livro: " + titulo + "\n" +
                "Autor:" + autor + "\n" +
                "isbn: " + isbn + "\n" +
                "Status: " + disponivel + "\n" +
                "--------------------------");
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getIsbn() {
        return isbn;
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
