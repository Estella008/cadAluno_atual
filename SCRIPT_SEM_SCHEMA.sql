-- ============================================
-- SCRIPT ALTERNATIVO - USAR SCHEMA PUBLIC
-- Use este script se não conseguir criar o schema "estella"
-- IMPORTANTE: Se usar este script, também precisa mudar o código Java
-- ============================================

-- Este script cria as tabelas no schema "public" (padrão)
-- Não precisa criar schema, o "public" já existe

-- Tabela de cantores
CREATE TABLE IF NOT EXISTS cantores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    pais VARCHAR(50),
    genero_musical VARCHAR(50)
);

-- Tabela de álbuns
CREATE TABLE IF NOT EXISTS albuns (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    ano_lancamento INTEGER,
    cantor_id INTEGER REFERENCES cantores(id) ON DELETE CASCADE
);

-- Tabela de músicas
CREATE TABLE IF NOT EXISTS musicas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    duracao TIME,
    album_id INTEGER REFERENCES albuns(id) ON DELETE CASCADE
);

-- ============================================
-- ATENÇÃO: Se usar este script, você precisa:
-- 1. Mudar ServicosMusica.java linha 26 para:
--    private static final String SCHEMA = "public";
-- 2. Ou remover o SCHEMA das queries SQL
-- ============================================



