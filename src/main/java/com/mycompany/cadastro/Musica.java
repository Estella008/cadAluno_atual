/*
 * Classe Musica - Representa uma música no sistema
 * @author Adaptado para sistema musical
 */
package com.mycompany.cadastro;

/**
 * Classe que representa uma Música no sistema de cadastro musical
 */
public class Musica {
    private int id;
    private String titulo;
    private String duracao;
    private int albumId;
    private String albumTitulo; // Para exibição na interface

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitulo() {
        return albumTitulo;
    }

    public void setAlbumTitulo(String albumTitulo) {
        this.albumTitulo = albumTitulo;
    }
}

