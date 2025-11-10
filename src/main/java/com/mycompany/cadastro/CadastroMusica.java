/*
 * CadastroMusica - Bean JSF para gerenciamento do sistema musical
 * @author Adaptado para sistema musical
 */
package com.mycompany.cadastro;

import com.mycompany.cadastro.banco.ServicosMusica;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Classe bean JSF que gerencia as operações do sistema de cadastro musical
 */
@Named(value = "cadastroMusica")
@SessionScoped
public class CadastroMusica implements Serializable {
    
    private String mensagem = "Bem vindo ao Sistema de Cadastro Musical!";
    
    // Objetos para cadastro
    private Cantor dadosCantor = new Cantor();
    private Album dadosAlbum = new Album();
    private Musica dadosMusica = new Musica();
    
    // Listas para exibição
    private List<Cantor> listaCantores;
    private List<Album> listaAlbuns;
    private List<Musica> listaMusicas;
    
    // IDs para edição/exclusão
    private int idCantorSelecionado;
    private int idAlbumSelecionado;
    private int idMusicaSelecionada;
    
    // Objetos para edição
    private Cantor cantorEditando;
    private Album albumEditando;
    private Musica musicaEditando;
    
    private ServicosMusica servicos = new ServicosMusica();
    
    // ========== OPERAÇÕES COM CANTORES ==========
    
    /**
     * Cadastra um novo cantor
     */
    public void cadastrarCantor() {
        int id = servicos.inserirCantor(dadosCantor);
        if (id > 0) {
            this.mensagem = "Cantor " + dadosCantor.getNome() + " cadastrado com sucesso! ID: " + id;
            this.dadosCantor = new Cantor(); // Limpa o formulário
            this.listaCantores = servicos.listarCantores(); // Atualiza a lista
        } else {
            this.mensagem = "Erro ao cadastrar cantor!";
        }
    }
    
    /**
     * Lista todos os cantores cadastrados
     */
    public void listarCantores() {
        this.listaCantores = servicos.listarCantores();
    }
    
    /**
     * Prepara os dados do cantor para edição
     */
    public void prepararEdicaoCantor() {
        if (idCantorSelecionado > 0) {
            this.cantorEditando = servicos.buscarCantorPorId(idCantorSelecionado);
            if (cantorEditando != null) {
                this.dadosCantor = cantorEditando;
                this.mensagem = "Editando cantor: " + cantorEditando.getNome();
            }
        }
    }
    
    /**
     * Atualiza os dados de um cantor
     */
    public void atualizarCantor() {
        if (servicos.atualizarCantor(dadosCantor)) {
            this.mensagem = "Cantor atualizado com sucesso!";
            this.dadosCantor = new Cantor();
            this.listaCantores = servicos.listarCantores();
        } else {
            this.mensagem = "Erro ao atualizar cantor!";
        }
    }
    
    /**
     * Exclui um cantor
     */
    public void excluirCantor() {
        if (idCantorSelecionado > 0) {
            if (servicos.excluirCantor(idCantorSelecionado)) {
                this.mensagem = "Cantor excluído com sucesso!";
                this.listaCantores = servicos.listarCantores();
                this.listaAlbuns = servicos.listarAlbuns(); // Atualiza álbuns também
            } else {
                this.mensagem = "Erro ao excluir cantor!";
            }
        }
    }
    
    /**
     * Prepara edição de cantor recebendo ID como parâmetro
     */
    public void prepararEdicaoCantorPorId(int id) {
        this.idCantorSelecionado = id;
        prepararEdicaoCantor();
    }
    
    /**
     * Exclui cantor recebendo ID como parâmetro
     */
    public void excluirCantorPorId(int id) {
        this.idCantorSelecionado = id;
        if (servicos.excluirCantor(idCantorSelecionado)) {
            this.mensagem = "Cantor excluído com sucesso!";
            this.listaCantores = servicos.listarCantores();
            this.listaAlbuns = servicos.listarAlbuns();
        } else {
            this.mensagem = "Erro ao excluir cantor!";
        }
    }
    
    // ========== OPERAÇÕES COM ÁLBUNS ==========
    
    /**
     * Cadastra um novo álbum
     */
    public void cadastrarAlbum() {
        int id = servicos.inserirAlbum(dadosAlbum);
        if (id > 0) {
            this.mensagem = "Álbum " + dadosAlbum.getTitulo() + " cadastrado com sucesso! ID: " + id;
            this.dadosAlbum = new Album(); // Limpa o formulário
            this.listaAlbuns = servicos.listarAlbuns(); // Atualiza a lista
        } else {
            this.mensagem = "Erro ao cadastrar álbum!";
        }
    }
    
    /**
     * Lista todos os álbuns cadastrados
     */
    public void listarAlbuns() {
        this.listaAlbuns = servicos.listarAlbuns();
    }
    
    /**
     * Prepara os dados do álbum para edição
     */
    public void prepararEdicaoAlbum() {
        if (idAlbumSelecionado > 0) {
            this.albumEditando = servicos.buscarAlbumPorId(idAlbumSelecionado);
            if (albumEditando != null) {
                this.dadosAlbum = albumEditando;
                this.mensagem = "Editando álbum: " + albumEditando.getTitulo();
            }
        }
    }
    
    /**
     * Atualiza os dados de um álbum
     */
    public void atualizarAlbum() {
        if (servicos.atualizarAlbum(dadosAlbum)) {
            this.mensagem = "Álbum atualizado com sucesso!";
            this.dadosAlbum = new Album();
            this.listaAlbuns = servicos.listarAlbuns();
            this.listaMusicas = servicos.listarMusicas(); // Atualiza músicas também
        } else {
            this.mensagem = "Erro ao atualizar álbum!";
        }
    }
    
    /**
     * Exclui um álbum
     */
    public void excluirAlbum() {
        if (idAlbumSelecionado > 0) {
            if (servicos.excluirAlbum(idAlbumSelecionado)) {
                this.mensagem = "Álbum excluído com sucesso!";
                this.listaAlbuns = servicos.listarAlbuns();
                this.listaMusicas = servicos.listarMusicas(); // Atualiza músicas também
            } else {
                this.mensagem = "Erro ao excluir álbum!";
            }
        }
    }
    
    /**
     * Prepara edição de álbum recebendo ID como parâmetro
     */
    public void prepararEdicaoAlbumPorId(int id) {
        this.idAlbumSelecionado = id;
        prepararEdicaoAlbum();
    }
    
    /**
     * Exclui álbum recebendo ID como parâmetro
     */
    public void excluirAlbumPorId(int id) {
        this.idAlbumSelecionado = id;
        if (servicos.excluirAlbum(idAlbumSelecionado)) {
            this.mensagem = "Álbum excluído com sucesso!";
            this.listaAlbuns = servicos.listarAlbuns();
            this.listaMusicas = servicos.listarMusicas();
        } else {
            this.mensagem = "Erro ao excluir álbum!";
        }
    }
    
    // ========== OPERAÇÕES COM MÚSICAS ==========
    
    /**
     * Cadastra uma nova música
     */
    public void cadastrarMusica() {
        int id = servicos.inserirMusica(dadosMusica);
        if (id > 0) {
            this.mensagem = "Música " + dadosMusica.getTitulo() + " cadastrada com sucesso! ID: " + id;
            this.dadosMusica = new Musica(); // Limpa o formulário
            this.listaMusicas = servicos.listarMusicas(); // Atualiza a lista
        } else {
            this.mensagem = "Erro ao cadastrar música!";
        }
    }
    
    /**
     * Lista todas as músicas cadastradas
     */
    public void listarMusicas() {
        this.listaMusicas = servicos.listarMusicas();
    }
    
    /**
     * Prepara os dados da música para edição
     */
    public void prepararEdicaoMusica() {
        if (idMusicaSelecionada > 0) {
            this.musicaEditando = servicos.buscarMusicaPorId(idMusicaSelecionada);
            if (musicaEditando != null) {
                this.dadosMusica = musicaEditando;
                this.mensagem = "Editando música: " + musicaEditando.getTitulo();
            }
        }
    }
    
    /**
     * Atualiza os dados de uma música
     */
    public void atualizarMusica() {
        if (servicos.atualizarMusica(dadosMusica)) {
            this.mensagem = "Música atualizada com sucesso!";
            this.dadosMusica = new Musica();
            this.listaMusicas = servicos.listarMusicas();
        } else {
            this.mensagem = "Erro ao atualizar música!";
        }
    }
    
    /**
     * Exclui uma música
     */
    public void excluirMusica() {
        if (idMusicaSelecionada > 0) {
            if (servicos.excluirMusica(idMusicaSelecionada)) {
                this.mensagem = "Música excluída com sucesso!";
                this.listaMusicas = servicos.listarMusicas();
            } else {
                this.mensagem = "Erro ao excluir música!";
            }
        }
    }
    
    /**
     * Prepara edição de música recebendo ID como parâmetro
     */
    public void prepararEdicaoMusicaPorId(int id) {
        this.idMusicaSelecionada = id;
        prepararEdicaoMusica();
    }
    
    /**
     * Exclui música recebendo ID como parâmetro
     */
    public void excluirMusicaPorId(int id) {
        this.idMusicaSelecionada = id;
        if (servicos.excluirMusica(idMusicaSelecionada)) {
            this.mensagem = "Música excluída com sucesso!";
            this.listaMusicas = servicos.listarMusicas();
        } else {
            this.mensagem = "Erro ao excluir música!";
        }
    }
    
    // ========== GETTERS E SETTERS ==========
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Cantor getDadosCantor() {
        return dadosCantor;
    }

    public void setDadosCantor(Cantor dadosCantor) {
        this.dadosCantor = dadosCantor;
    }

    public Album getDadosAlbum() {
        return dadosAlbum;
    }

    public void setDadosAlbum(Album dadosAlbum) {
        this.dadosAlbum = dadosAlbum;
    }

    public Musica getDadosMusica() {
        return dadosMusica;
    }

    public void setDadosMusica(Musica dadosMusica) {
        this.dadosMusica = dadosMusica;
    }

    public List<Cantor> getListaCantores() {
        if (listaCantores == null) {
            listaCantores = servicos.listarCantores();
        }
        return listaCantores;
    }

    public void setListaCantores(List<Cantor> listaCantores) {
        this.listaCantores = listaCantores;
    }

    public List<Album> getListaAlbuns() {
        if (listaAlbuns == null) {
            listaAlbuns = servicos.listarAlbuns();
        }
        return listaAlbuns;
    }

    public void setListaAlbuns(List<Album> listaAlbuns) {
        this.listaAlbuns = listaAlbuns;
    }

    public List<Musica> getListaMusicas() {
        if (listaMusicas == null) {
            listaMusicas = servicos.listarMusicas();
        }
        return listaMusicas;
    }

    public void setListaMusicas(List<Musica> listaMusicas) {
        this.listaMusicas = listaMusicas;
    }

    public int getIdCantorSelecionado() {
        return idCantorSelecionado;
    }

    public void setIdCantorSelecionado(int idCantorSelecionado) {
        this.idCantorSelecionado = idCantorSelecionado;
    }

    public int getIdAlbumSelecionado() {
        return idAlbumSelecionado;
    }

    public void setIdAlbumSelecionado(int idAlbumSelecionado) {
        this.idAlbumSelecionado = idAlbumSelecionado;
    }

    public int getIdMusicaSelecionada() {
        return idMusicaSelecionada;
    }

    public void setIdMusicaSelecionada(int idMusicaSelecionada) {
        this.idMusicaSelecionada = idMusicaSelecionada;
    }
}

