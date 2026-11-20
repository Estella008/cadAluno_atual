# 🎵 Cadastro Musical — CRUD com Jakarta EE, JSF e PostgreSQL

Aplicação web de cadastro de **cantores, álbuns e músicas**, com operações completas de CRUD e relacionamentos entre as entidades. Desenvolvida na disciplina de Programação Web (Sistemas de Informação – IFMG Ouro Branco).

## ✨ Funcionalidades

- CRUD completo (cadastrar, listar, editar e excluir) para **Cantores**, **Álbuns** e **Músicas**
- Listagens com dados relacionados (álbum mostra o cantor; música mostra o álbum)
- Exclusão em cascata: remover um cantor remove seus álbuns e músicas

## 🗂️ Modelo de dados

```text
Cantor (id, nome, pais, genero_musical)
  └── 1:N ── Álbum (id, titulo, ano_lancamento, cantor_id)
                └── 1:N ── Música (id, titulo, duracao, album_id)
```

O script de criação das tabelas está em [`script_criacao_tabelas.sql`](script_criacao_tabelas.sql) (schema `estella_musica`).

## 🛠️ Tecnologias

- Java 11
- Jakarta EE 10 · JSF (Jakarta Faces) 4.0 · CDI (Weld)
- JDBC + PostgreSQL
- Maven

## 🚀 Como executar

1. Crie as tabelas executando `script_criacao_tabelas.sql` no seu PostgreSQL.
2. Ajuste URL, usuário e senha do banco em `src/main/java/com/mycompany/cadastro/banco/Conexao.java`.
3. Gere o pacote e publique em um servidor Jakarta EE (Tomcat ou Payara):
   ```bash
   mvn package
   ```
4. Acesse `http://localhost:8080/cadastro/cad.xhtml`.

Cadastre na ordem **Cantores → Álbuns → Músicas**, pois álbuns e músicas referenciam o ID do registro pai.

## 📁 Estrutura

```text
src/main/java/com/mycompany/cadastro/
├── Cantor.java, Album.java, Musica.java   # modelos
├── CadastroMusica.java                    # bean JSF
└── banco/
    ├── Conexao.java                       # conexão JDBC
    └── ServicosMusica.java                # operações CRUD
src/main/webapp/cad.xhtml                  # interface (formulários e listagens)
docs/                                      # scripts SQL alternativos e notas de configuração
```
