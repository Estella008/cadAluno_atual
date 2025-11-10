/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cadastro;

import com.mycompany.cadastro.banco.ServicosAluno;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author janio.silva
 */
@Named
@SessionScoped
public class Cadastro implements Serializable  {
    private String mensagem="Bem vindo ao cadastro de Alunos!";
    private Aluno dadosAluno = new Aluno();
    private List<Aluno> listaAlunos ;
    
    public void cadastrarAluno(){
        ServicosAluno s = new ServicosAluno();
        int id = s.inserirAluno(dadosAluno);
        this.listaAlunos=s.listarAlunosCadastrados();
        this.mensagem="Aluno "+id+" cadastrado com sucesso!";
    }   

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Aluno getDadosAluno() {
        return dadosAluno;
    }

    public void setDadosAluno(Aluno dadosAluno) {
        this.dadosAluno = dadosAluno;
    }
    

    public void listarAlunosCadastrados(){
        ServicosAluno s = new ServicosAluno();
        this.listaAlunos=s.listarAlunosCadastrados();
        
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }
    
    
}
