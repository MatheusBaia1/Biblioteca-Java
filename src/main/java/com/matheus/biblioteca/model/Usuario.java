package com.matheus.biblioteca.model;

public class Usuario {
    private Integer id;
    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    @Override
    public String toString() {
        return "==========================" + "\n" +
                "nome: " + nome + "\n" +
                "email: " + email + "\n" +
                "--------------------------";
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
}
