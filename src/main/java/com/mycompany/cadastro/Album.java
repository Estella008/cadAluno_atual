/*
 * Classe Album - Representa um álbum musical no sistema
 * @author Adaptado para sistema musical
 */
package com.mycompany.cadastro;

/**
 * Classe que representa um Álbum no sistema de cadastro musical
 */
public class Album {
    private int id;
    private String titulo;
    private int anoLancamento;
    private int cantorId;
    private String cantorNome; // Para exibição na interface

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

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getCantorId() {
        return cantorId;
    }

    public void setCantorId(int cantorId) {
        this.cantorId = cantorId;
    }

    public String getCantorNome() {
        return cantorNome;
    }

    public void setCantorNome(String cantorNome) {
        this.cantorNome = cantorNome;
    }
}

