/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cadastro.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janio.silva
 */
public class Conexao {
    
    public Connection obterConexao(){
        String usuario="aula",senha="aula",
                url="jdbc:postgresql://200.18.128.54/aula";
    
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, usuario,senha);
            return c;
        }catch (SQLException e){
            System.out.println("Erro: "+e.getMessage());
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: "+ex.getMessage());
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
