-- ============================================
-- SCRIPT FINAL - EXECUTE ESTE NO DBEAVER
-- ============================================

-- 1. Criar schema "estella"
CREATE SCHEMA IF NOT EXISTS estella;

-- 2. Criar tabelas no schema "estella"
CREATE TABLE IF NOT EXISTS estella.cantores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    pais VARCHAR(50),
    genero_musical VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS estella.albuns (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    ano_lancamento INTEGER,
    cantor_id INTEGER REFERENCES estella.cantores(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS estella.musicas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    duracao TIME,
    album_id INTEGER REFERENCES estella.albuns(id) ON DELETE CASCADE
);

-- 3. Verificar se criou corretamente (descomente para executar)
-- SELECT table_name FROM information_schema.tables WHERE table_schema = 'estella';



