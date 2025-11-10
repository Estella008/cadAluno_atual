================================================================================
                    SISTEMA DE CADASTRO MUSICAL
                    CRUD - Cantores, Álbuns e Músicas
================================================================================

TEMA ESCOLHIDO:
---------------
Sistema de Cadastro Musical com três entidades relacionadas:
- Cantores (Artistas)
- Álbuns (Vinculados a Cantores)
- Músicas (Vinculadas a Álbuns)

ENTIDADES E RELACIONAMENTOS:
----------------------------
1. CANTORES
   - id (PK, Serial)
   - nome (VARCHAR 100)
   - pais (VARCHAR 50)
   - genero_musical (VARCHAR 50)

2. ÁLBUNS
   - id (PK, Serial)
   - titulo (VARCHAR 200)
   - ano_lancamento (INTEGER)
   - cantor_id (FK → cantores.id)

3. MÚSICAS
   - id (PK, Serial)
   - titulo (VARCHAR 200)
   - duracao (TIME)
   - album_id (FK → albuns.id)

RELACIONAMENTOS:
- Um Cantor pode ter vários Álbuns (1:N)
- Um Álbum pode ter várias Músicas (1:N)
- CASCADE DELETE: Ao excluir um cantor, seus álbuns são excluídos
- CASCADE DELETE: Ao excluir um álbum, suas músicas são excluídas

OPERАÇÕES CRUD IMPLEMENTADAS:
-----------------------------
Para CANTORES:
  ✓ Create (Cadastrar)
  ✓ Read (Listar)
  ✓ Update (Editar)
  ✓ Delete (Excluir)

Para ÁLBUNS:
  ✓ Create (Cadastrar)
  ✓ Read (Listar com nome do cantor)
  ✓ Update (Editar)
  ✓ Delete (Excluir)

Para MÚSICAS:
  ✓ Create (Cadastrar)
  ✓ Read (Listar com título do álbum)
  ✓ Update (Editar)
  ✓ Delete (Excluir)

TECNOLOGIAS UTILIZADAS:
----------------------
- Java 11
- Jakarta EE 10
- JSF (JavaServer Faces) 4.0
- PostgreSQL
- JDBC para conexão com banco de dados
- Maven para gerenciamento de dependências

ESTRUTURA DO PROJETO:
--------------------
src/main/java/com/mycompany/cadastro/
  - Cantor.java (Classe de modelo)
  - Album.java (Classe de modelo)
  - Musica.java (Classe de modelo)
  - CadastroMusica.java (Bean JSF - gerenciamento)
  - banco/
    - Conexao.java (Classe de conexão com banco)
    - ServicosMusica.java (Operações CRUD)

src/main/webapp/
  - cad.xhtml (Interface web - formulários e listagens)

script_criacao_tabelas.sql (Script SQL para criação das tabelas)

CONFIGURAÇÃO DO BANCO DE DADOS:
-------------------------------
Servidor (Laboratório): 10.90.24.54
Servidor (Casa): 200.18.128.54
Banco de dados: aula
Usuário: aula
Senha: aula
Porta: 5432 (padrão PostgreSQL)

CONFIGURAÇÃO: O sistema está configurado para usar o schema "estella_musica".

COMO EXECUTAR O SISTEMA:
-----------------------
1. CONFIGURAÇÃO DO BANCO:
   a) Abra o DBeaver ou pgAdmin
   b) Conecte ao banco "aula" no servidor (10.90.24.54 ou 200.18.128.54)
   c) Execute o script script_criacao_tabelas.sql
   d) O script já está configurado para usar o schema "estella_musica"

2. CONFIGURAÇÃO DO CÓDIGO:
   a) Abra o projeto no NetBeans
   b) O código já está configurado para usar o schema "estella_musica"
   c) Se necessário, verifique a classe ServicosMusica.java linha 26

3. EXECUTAR:
   a) Compile o projeto (Build)
   b) Execute no servidor (Tomcat ou Payara)
   c) Acesse: http://localhost:8080/cadastro/cad.xhtml

4. USAR O SISTEMA:
   a) Cadastre primeiro os CANTORES
   b) Depois cadastre os ÁLBUNS (informando o ID do cantor)
   c) Por fim, cadastre as MÚSICAS (informando o ID do álbum)
   d) Use os botões "Editar" e "Excluir" para gerenciar os registros

EXEMPLO DE DADOS PARA TESTE:
----------------------------
CANTORES:
- Nome: Beyoncé, País: EUA, Gênero: Pop/R&B
- Nome: Anitta, País: Brasil, Gênero: Funk/Pop
- Nome: The Weeknd, País: Canadá, Gênero: R&B/Pop

ÁLBUNS (após cadastrar os cantores):
- Título: Renaissance, Ano: 2022, Cantor ID: [ID do cantor]
- Título: Versions of Me, Ano: 2022, Cantor ID: [ID do cantor]

MÚSICAS (após cadastrar os álbuns):
- Título: BREAK MY SOUL, Duração: 00:04:38, Álbum ID: [ID do álbum]
- Título: Envolver, Duração: 00:03:13, Álbum ID: [ID do álbum]

OBSERVAÇÕES IMPORTANTES:
-----------------------
1. A ordem de cadastro é importante: Cantores → Álbuns → Músicas
2. Ao excluir um cantor, todos os seus álbuns e músicas serão excluídos (CASCADE)
3. Ao excluir um álbum, todas as suas músicas serão excluídas (CASCADE)
4. A duração da música deve estar no formato TIME (HH:MM:SS ou MM:SS)
5. Certifique-se de que o ID do cantor/álbum existe antes de cadastrar

ARQUIVOS PARA ENTREGA:
---------------------
✓ Script SQL (script_criacao_tabelas.sql)
✓ Código-fonte completo (pasta src/)
✓ Arquivo de configuração (pom.xml, persistence.xml)
✓ Documentação (README.txt)

AUTOR:
------
[Seu Nome]
[Data]

================================================================================

