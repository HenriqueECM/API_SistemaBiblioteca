package com.example.biblioteca.model;

public class Livro {
    private int id, anoPublicacao;
    private String titulo, autor;

    public Livro(int id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.anoPublicacao = anoPublicacao;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Livro() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
