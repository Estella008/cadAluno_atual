/*
 * ServicosMusica - Classe de serviços para operações CRUD no sistema musical
 * @author Adaptado para sistema musical
 */
package com.mycompany.cadastro.banco;

import com.mycompany.cadastro.Cantor;
import com.mycompany.cadastro.Album;
import com.mycompany.cadastro.Musica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que contém todas as operações CRUD para Cantores, Álbuns e Músicas
 * Schema configurado: estella_musica
 */
public class ServicosMusica {
    
    // Schema do banco de dados - estella_musica
    private static final String SCHEMA = "estella_musica";
    
    // ========== OPERAÇÕES CRUD PARA CANTORES ==========
    
    /**
     * Insere um novo cantor no banco de dados
     * @param dados Objeto Cantor com os dados a serem inseridos
     * @return ID do cantor inserido, ou 0 se houver erro
     */
    public int inserirCantor(Cantor dados) {
        Connection con = null;
        try {
            Conexao c = new Conexao();
            con = c.obterConexao();
            
            if (con == null) {
                System.out.println("ERRO: Não foi possível estabelecer conexão com o banco de dados!");
                return 0;
            }
            
            String SQL = "INSERT INTO " + SCHEMA + ".cantores (nome, pais, genero_musical) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, dados.getNome());
            p.setString(2, dados.getPais());
            p.setString(3, dados.getGeneroMusical());
            ResultSet r = p.executeQuery();
            if (r.next()) {
                int id = r.getInt("id");
                return id;
            }
        } catch (SQLException ex) {
            System.out.println("ERRO ao inserir cantor: " + ex.getMessage());
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, "Erro SQL", ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, "Erro ao fechar conexão", ex);
                }
            }
        }
        return 0;
    }
    
    /**
     * Lista todos os cantores cadastrados
     * @return Lista de cantores ordenados por ID decrescente
     */
    public List<Cantor> listarCantores() {
        Connection con = null;
        try {
            List<Cantor> lista = new ArrayList<Cantor>();
            Conexao c = new Conexao();
            con = c.obterConexao();
            
            if (con == null) {
                System.out.println("ERRO: Não foi possível estabelecer conexão com o banco de dados!");
                System.out.println("Verifique se:");
                System.out.println("1. O servidor está acessível (10.90.24.54 ou 200.18.128.54)");
                System.out.println("2. O schema 'estella' foi criado");
                System.out.println("3. As tabelas foram criadas");
                return new ArrayList<Cantor>(); // Retorna lista vazia ao invés de null
            }
            
            String SQL = "SELECT * FROM " + SCHEMA + ".cantores ORDER BY id DESC";
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                Cantor atual = new Cantor();
                atual.setId(r.getInt("id"));
                atual.setNome(r.getString("nome"));
                atual.setPais(r.getString("pais"));
                atual.setGeneroMusical(r.getString("genero_musical"));
                lista.add(atual);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("ERRO ao listar cantores: " + ex.getMessage());
            if (ex.getMessage().contains("does not exist")) {
                System.out.println("ERRO: O schema '" + SCHEMA + "' ou a tabela 'cantores' não existe!");
                System.out.println("Execute o script SQL: script_criacao_tabelas.sql");
            }
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, "Erro SQL", ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, "Erro ao fechar conexão", ex);
                }
            }
        }
        return new ArrayList<Cantor>(); // Retorna lista vazia ao invés de null
    }
    
    /**
     * Atualiza os dados de um cantor
     * @param dados Objeto Cantor com os dados atualizados
     * @return true se atualizado com sucesso, false caso contrário
     */
    public boolean atualizarCantor(Cantor dados) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "UPDATE " + SCHEMA + ".cantores SET nome = ?, pais = ?, genero_musical = ? WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, dados.getNome());
            p.setString(2, dados.getPais());
            p.setString(3, dados.getGeneroMusical());
            p.setInt(4, dados.getId());
            int linhasAfetadas = p.executeUpdate();
            con.close();
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Exclui um cantor do banco de dados
     * @param id ID do cantor a ser excluído
     * @return true se excluído com sucesso, false caso contrário
     */
    public boolean excluirCantor(int id) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "DELETE FROM " + SCHEMA + ".cantores WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, id);
            int linhasAfetadas = p.executeUpdate();
            con.close();
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Busca um cantor pelo ID
     * @param id ID do cantor
     * @return Objeto Cantor ou null se não encontrado
     */
    public Cantor buscarCantorPorId(int id) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT * FROM " + SCHEMA + ".cantores WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, id);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                Cantor cantor = new Cantor();
                cantor.setId(r.getInt("id"));
                cantor.setNome(r.getString("nome"));
                cantor.setPais(r.getString("pais"));
                cantor.setGeneroMusical(r.getString("genero_musical"));
                con.close();
                return cantor;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // ========== OPERAÇÕES CRUD PARA ÁLBUNS ==========
    
    /**
     * Insere um novo álbum no banco de dados
     * @param dados Objeto Album com os dados a serem inseridos
     * @return ID do álbum inserido, ou 0 se houver erro
     */
    public int inserirAlbum(Album dados) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "INSERT INTO " + SCHEMA + ".albuns (titulo, ano_lancamento, cantor_id) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, dados.getTitulo());
            p.setInt(2, dados.getAnoLancamento());
            p.setInt(3, dados.getCantorId());
            ResultSet r = p.executeQuery();
            if (r.next()) {
                int id = r.getInt("id");
                con.close();
                return id;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * Lista todos os álbuns cadastrados com nome do cantor
     * @return Lista de álbuns ordenados por ID decrescente
     */
    public List<Album> listarAlbuns() {
        try {
            List<Album> lista = new ArrayList<Album>();
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT a.id, a.titulo, a.ano_lancamento, a.cantor_id, c.nome as cantor_nome " +
                         "FROM " + SCHEMA + ".albuns a " +
                         "LEFT JOIN " + SCHEMA + ".cantores c ON a.cantor_id = c.id " +
                         "ORDER BY a.id DESC";
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                Album atual = new Album();
                atual.setId(r.getInt("id"));
                atual.setTitulo(r.getString("titulo"));
                atual.setAnoLancamento(r.getInt("ano_lancamento"));
                atual.setCantorId(r.getInt("cantor_id"));
                atual.setCantorNome(r.getString("cantor_nome"));
                lista.add(atual);
            }
            con.close();
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro: "+ex.getMessage());
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Atualiza os dados de um álbum
     * @param dados Objeto Album com os dados atualizados
     * @return true se atualizado com sucesso, false caso contrário
     */
    public boolean atualizarAlbum(Album dados) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "UPDATE " + SCHEMA + ".albuns SET titulo = ?, ano_lancamento = ?, cantor_id = ? WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, dados.getTitulo());
            p.setInt(2, dados.getAnoLancamento());
            p.setInt(3, dados.getCantorId());
            p.setInt(4, dados.getId());
            int linhasAfetadas = p.executeUpdate();
            con.close();
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Exclui um álbum do banco de dados
     * @param id ID do álbum a ser excluído
     * @return true se excluído com sucesso, false caso contrário
     */
    public boolean excluirAlbum(int id) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "DELETE FROM " + SCHEMA + ".albuns WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, id);
            int linhasAfetadas = p.executeUpdate();
            con.close();
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Busca um álbum pelo ID
     * @param id ID do álbum
     * @return Objeto Album ou null se não encontrado
     */
    public Album buscarAlbumPorId(int id) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT a.id, a.titulo, a.ano_lancamento, a.cantor_id, c.nome as cantor_nome " +
                         "FROM " + SCHEMA + ".albuns a " +
                         "LEFT JOIN " + SCHEMA + ".cantores c ON a.cantor_id = c.id " +
                         "WHERE a.id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, id);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                Album album = new Album();
                album.setId(r.getInt("id"));
                album.setTitulo(r.getString("titulo"));
                album.setAnoLancamento(r.getInt("ano_lancamento"));
                album.setCantorId(r.getInt("cantor_id"));
                album.setCantorNome(r.getString("cantor_nome"));
                con.close();
                return album;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // ========== OPERAÇÕES CRUD PARA MÚSICAS ==========
    
    /**
     * Insere uma nova música no banco de dados
     * @param dados Objeto Musica com os dados a serem inseridos
     * @return ID da música inserida, ou 0 se houver erro
     */
    public int inserirMusica(Musica dados) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "INSERT INTO " + SCHEMA + ".musicas (titulo, duracao, album_id) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, dados.getTitulo());
            p.setString(2, dados.getDuracao());
            p.setInt(3, dados.getAlbumId());
            ResultSet r = p.executeQuery();
            if (r.next()) {
                int id = r.getInt("id");
                con.close();
                return id;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * Lista todas as músicas cadastradas com título do álbum
     * @return Lista de músicas ordenadas por ID decrescente
     */
    public List<Musica> listarMusicas() {
        try {
            List<Musica> lista = new ArrayList<Musica>();
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT m.id, m.titulo, m.duracao, m.album_id, a.titulo as album_titulo " +
                         "FROM " + SCHEMA + ".musicas m " +
                         "LEFT JOIN " + SCHEMA + ".albuns a ON m.album_id = a.id " +
                         "ORDER BY m.id DESC";
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                Musica atual = new Musica();
                atual.setId(r.getInt("id"));
                atual.setTitulo(r.getString("titulo"));
                atual.setDuracao(r.getString("duracao"));
                atual.setAlbumId(r.getInt("album_id"));
                atual.setAlbumTitulo(r.getString("album_titulo"));
                lista.add(atual);
            }
            con.close();
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro: "+ex.getMessage());
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Atualiza os dados de uma música
     * @param dados Objeto Musica com os dados atualizados
     * @return true se atualizado com sucesso, false caso contrário
     */
    public boolean atualizarMusica(Musica dados) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "UPDATE " + SCHEMA + ".musicas SET titulo = ?, duracao = ?, album_id = ? WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, dados.getTitulo());
            p.setString(2, dados.getDuracao());
            p.setInt(3, dados.getAlbumId());
            p.setInt(4, dados.getId());
            int linhasAfetadas = p.executeUpdate();
            con.close();
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Exclui uma música do banco de dados
     * @param id ID da música a ser excluída
     * @return true se excluída com sucesso, false caso contrário
     */
    public boolean excluirMusica(int id) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "DELETE FROM " + SCHEMA + ".musicas WHERE id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, id);
            int linhasAfetadas = p.executeUpdate();
            con.close();
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Busca uma música pelo ID
     * @param id ID da música
     * @return Objeto Musica ou null se não encontrado
     */
    public Musica buscarMusicaPorId(int id) {
        try {
            Conexao c = new Conexao();
            Connection con = c.obterConexao();
            String SQL = "SELECT m.id, m.titulo, m.duracao, m.album_id, a.titulo as album_titulo " +
                         "FROM " + SCHEMA + ".musicas m " +
                         "LEFT JOIN " + SCHEMA + ".albuns a ON m.album_id = a.id " +
                         "WHERE m.id = ?";
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, id);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                Musica musica = new Musica();
                musica.setId(r.getInt("id"));
                musica.setTitulo(r.getString("titulo"));
                musica.setDuracao(r.getString("duracao"));
                musica.setAlbumId(r.getInt("album_id"));
                musica.setAlbumTitulo(r.getString("album_titulo"));
                con.close();
                return musica;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicosMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}


