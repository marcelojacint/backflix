CREATE TABLE filmes (
    id UUID PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    sinopse VARCHAR(200),
    nota INT,
    data_lancamento VARCHAR(10),
    genero_id BIGINT,
    duracao_minutos INT,
    classificacao_indicativa VARCHAR(10),

    CONSTRAINT fk_filme_genero
        FOREIGN KEY (genero_id) REFERENCES generos(id)
);