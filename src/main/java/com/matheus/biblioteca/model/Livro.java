package com.matheus.biblioteca.model;

public class Livro {
    private String titulo;
    private String autor;
    private int isbn;
    private boolean disponivel;

    public Livro(String titulo, String autor, int isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }
    @Override
    public String toString() {
        return ("Livro: " + titulo + "-" + autor + "-" + isbn + "-" + disponivel );
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public int getIsbn() {
        return isbn;
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
