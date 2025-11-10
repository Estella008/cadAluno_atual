-- ============================================
-- SCRIPT SQL PARA CRIAÇÃO DO SISTEMA MUSICAL
-- ============================================
-- Schema: estella_musica
-- Execute este script no DBeaver ou pgAdmin conectado ao banco "aula"

-- Criação do schema
CREATE SCHEMA IF NOT EXISTS estella_musica;

-- ============================================
-- TABELA: CANTORES
-- ============================================
-- Armazena informações dos cantores/artistas
CREATE TABLE estella_musica.cantores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    pais VARCHAR(50),
    genero_musical VARCHAR(50)
);

-- Comentários nas colunas
COMMENT ON TABLE estella_musica.cantores IS 'Tabela que armazena informações dos cantores';
COMMENT ON COLUMN estella_musica.cantores.id IS 'Identificador único do cantor';
COMMENT ON COLUMN estella_musica.cantores.nome IS 'Nome do cantor/artista';
COMMENT ON COLUMN estella_musica.cantores.pais IS 'País de origem do cantor';
COMMENT ON COLUMN estella_musica.cantores.genero_musical IS 'Gênero musical do cantor';

-- ============================================
-- TABELA: ÁLBUNS
-- ============================================
-- Armazena informações dos álbuns musicais
CREATE TABLE estella_musica.albuns (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    ano_lancamento INTEGER,
    cantor_id INTEGER REFERENCES estella_musica.cantores(id) ON DELETE CASCADE
);

-- Comentários nas colunas
COMMENT ON TABLE estella_musica.albuns IS 'Tabela que armazena informações dos álbuns';
COMMENT ON COLUMN estella_musica.albuns.id IS 'Identificador único do álbum';
COMMENT ON COLUMN estella_musica.albuns.titulo IS 'Título do álbum';
COMMENT ON COLUMN estella_musica.albuns.ano_lancamento IS 'Ano de lançamento do álbum';
COMMENT ON COLUMN estella_musica.albuns.cantor_id IS 'Referência ao cantor (chave estrangeira)';

-- ============================================
-- TABELA: MÚSICAS
-- ============================================
-- Armazena informações das músicas
CREATE TABLE estella_musica.musicas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    duracao TIME,
    album_id INTEGER REFERENCES estella_musica.albuns(id) ON DELETE CASCADE
);

-- Comentários nas colunas
COMMENT ON TABLE estella_musica.musicas IS 'Tabela que armazena informações das músicas';
COMMENT ON COLUMN estella_musica.musicas.id IS 'Identificador único da música';
COMMENT ON COLUMN estella_musica.musicas.titulo IS 'Título da música';
COMMENT ON COLUMN estella_musica.musicas.duracao IS 'Duração da música (formato TIME)';
COMMENT ON COLUMN estella_musica.musicas.album_id IS 'Referência ao álbum (chave estrangeira)';

-- ============================================
-- DADOS DE EXEMPLO (OPCIONAL)
-- ============================================
-- Descomente as linhas abaixo para inserir dados de exemplo

/*
-- Inserir cantores de exemplo
INSERT INTO estella_musica.cantores (nome, pais, genero_musical) VALUES
('Beyoncé', 'EUA', 'Pop/R&B'),
('Anitta', 'Brasil', 'Funk/Pop'),
('The Weeknd', 'Canadá', 'R&B/Pop'),
('Ludmilla', 'Brasil', 'Funk');

-- Inserir álbuns de exemplo (substitua os IDs pelos IDs reais dos cantores inseridos)
INSERT INTO estella_musica.albuns (titulo, ano_lancamento, cantor_id) VALUES
('Renaissance', 2022, 1),
('Versions of Me', 2022, 2),
('Dawn FM', 2022, 3),
('Numanice #2', 2022, 4);

-- Inserir músicas de exemplo (substitua os IDs pelos IDs reais dos álbuns inseridos)
INSERT INTO estella_musica.musicas (titulo, duracao, album_id) VALUES
('BREAK MY SOUL', '00:04:38', 1),
('Envolver', '00:03:13', 2),
('Take My Breath', '00:03:40', 3),
('Numanice', '00:02:47', 4);
*/

-- ============================================
-- VERIFICAÇÃO
-- ============================================
-- Execute estas queries para verificar se as tabelas foram criadas corretamente

-- Verificar tabelas criadas
SELECT table_name 
FROM information_schema.tables 
WHERE table_schema = 'estella_musica';

-- Verificar estrutura das tabelas
SELECT column_name, data_type, is_nullable
FROM information_schema.columns
WHERE table_schema = 'estella_musica'
ORDER BY table_name, ordinal_position;

