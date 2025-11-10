/*
 * Classe Cantor - Representa um artista/cantor no sistema
 * @author Adaptado para sistema musical
 */
package com.mycompany.cadastro;

/**
 * Classe que representa um Cantor no sistema de cadastro musical
 */
public class Cantor {
    private int id;
    private String nome;
    private String pais;
    private String generoMusical;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }
}

