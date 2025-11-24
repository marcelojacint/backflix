CREATE TABLE series (
    id UUID PRIMARY KEY,
    titulo_serie VARCHAR(100) NOT NULL,
    sinopse VARCHAR(1000),
    nota DOUBLE,
    data_lancamento VARCHAR(10) NOT NULL,
    quantidade_temporadas INT,
    quantidade_episodios INT NOT NULL,
    genero_id BIGINT NOT NULL,
    classificacao_idade VARCHAR(20) NOT NULL,

    CONSTRAINT fk_serie_genero
        FOREIGN KEY (genero_id) REFERENCES generos(id)
);