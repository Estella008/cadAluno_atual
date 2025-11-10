-- ============================================
-- SCRIPT CORRIGIDO - EXECUTE PASSO A PASSO
-- ============================================

-- PASSO 1: Criar o schema primeiro (EXECUTE ESTA LINHA PRIMEIRO)
CREATE SCHEMA IF NOT EXISTS estella;

-- PASSO 2: Depois execute as tabelas (EXECUTE ESTAS LINHAS DEPOIS)

-- Tabela de cantores
CREATE TABLE IF NOT EXISTS estella.cantores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    pais VARCHAR(50),
    genero_musical VARCHAR(50)
);

-- Tabela de álbuns
CREATE TABLE IF NOT EXISTS estella.albuns (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    ano_lancamento INTEGER,
    cantor_id INTEGER REFERENCES estella.cantores(id) ON DELETE CASCADE
);

-- Tabela de músicas
CREATE TABLE IF NOT EXISTS estella.musicas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    duracao TIME,
    album_id INTEGER REFERENCES estella.albuns(id) ON DELETE CASCADE
);

-- ============================================
-- VERIFICAÇÃO (Execute esta query para verificar)
-- ============================================
-- SELECT table_name FROM information_schema.tables WHERE table_schema = 'estella';



