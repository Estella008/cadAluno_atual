-- ============================================
-- SCRIPT PARA CRIAR SCHEMA E TABELAS
-- Schema: estella_musica
-- ============================================

-- 1. Criar schema "estella_musica"
CREATE SCHEMA IF NOT EXISTS estella_musica;

-- 2. Criar tabelas no schema "estella_musica"
CREATE TABLE IF NOT EXISTS estella_musica.cantores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    pais VARCHAR(50),
    genero_musical VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS estella_musica.albuns (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    ano_lancamento INTEGER,
    cantor_id INTEGER REFERENCES estella_musica.cantores(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS estella_musica.musicas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    duracao TIME,
    album_id INTEGER REFERENCES estella_musica.albuns(id) ON DELETE CASCADE
);

-- ============================================
-- VERIFICAÇÃO
-- ============================================
-- Execute esta query para verificar:
-- SELECT table_name FROM information_schema.tables WHERE table_schema = 'estella_musica';
-- 
-- Deve retornar 3 tabelas:
-- - cantores
-- - albuns
-- - musicas



