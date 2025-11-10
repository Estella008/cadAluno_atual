-- ============================================
-- SCRIPT RÁPIDO PARA EXECUTAR NO DBEAVER
-- Copie e cole TUDO no DBeaver e execute
-- ============================================

-- Criar schema
CREATE SCHEMA IF NOT EXISTS estella;

-- Criar tabela de cantores
CREATE TABLE estella.cantores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    pais VARCHAR(50),
    genero_musical VARCHAR(50)
);

-- Criar tabela de álbuns
CREATE TABLE estella.albuns (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    ano_lancamento INTEGER,
    cantor_id INTEGER REFERENCES estella.cantores(id) ON DELETE CASCADE
);

-- Criar tabela de músicas
CREATE TABLE estella.musicas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    duracao TIME,
    album_id INTEGER REFERENCES estella.albuns(id) ON DELETE CASCADE
);

-- Verificar se foi criado (execute esta parte separadamente se quiser)
-- SELECT table_name FROM information_schema.tables WHERE table_schema = 'estella';



