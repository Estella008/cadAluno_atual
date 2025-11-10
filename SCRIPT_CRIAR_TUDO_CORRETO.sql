-- ============================================
-- SCRIPT COMPLETO - CRIAR SCHEMA E TABELAS
-- Execute este script NO DBEAVER
-- ============================================

-- PASSO 1: Criar o schema "estella"
CREATE SCHEMA IF NOT EXISTS estella;

-- PASSO 2: Criar as tabelas no schema "estella"
-- (Note o prefixo "estella." antes de cada nome de tabela)

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
-- VERIFICAÇÃO (Execute esta query para confirmar)
-- ============================================
-- SELECT table_name 
-- FROM information_schema.tables 
-- WHERE table_schema = 'estella'
-- ORDER BY table_name;

-- Deve retornar apenas 3 linhas:
-- - cantores
-- - albuns
-- - musicas



