/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cadastro.banco;

import com.mycompany.cadastro.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janio.silva
 */
public class ServicosAluno {

    public int inserirAluno(Aluno dados) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "INSERT INTO academico.alunos (nome,  cpf, endereco, email, cep, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id ";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, dados.getNome());
            p.setString(2, dados.getCpf());
            p.setString(3, dados.getEndereco());
            p.setString(4, dados.getEmail());
            p.setString(5, dados.getCep());
            p.setString(6, dados.getCidade());
            p.setString(7, dados.getEstado());
            ResultSet r = p.executeQuery();
            if (r.next()) {
                con.close();
                return r.getInt("id");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Aluno> listarAlunosCadastrados() {
        try {
            List<Aluno> lista = new ArrayList<Aluno>();
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT * FROM academico.alunos ORDER BY id DESC";
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                Aluno atual = new Aluno();
                atual.setCep(r.getString("cep"));
                atual.setCidade(r.getString("cidade"));
                atual.setCpf(r.getString("cpf"));
                atual.setEmail(r.getString("email"));
                atual.setEndereco(r.getString("endereco"));
                atual.setEstado(r.getString("estado"));
                atual.setNome(r.getString("nome"));
                lista.add(atual);
            }
            con.close();
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro: "+ex.getMessage());
            Logger.getLogger(ServicosAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
