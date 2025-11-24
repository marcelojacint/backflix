CREATE TABLE avaliacoes (
    id UUID PRIMARY KEY,

    id_usuario UUID NOT NULL,
    id_filme UUID,
    id_serie UUID,

    comentario VARCHAR(300) NOT NULL,
    data_avaliacao DATE NOT NULL,

    CONSTRAINT fk_avaliacao_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    CONSTRAINT fk_avaliacao_filme   FOREIGN KEY (id_filme) REFERENCES filmes(id),
    CONSTRAINT fk_avaliacao_serie   FOREIGN KEY (id_serie)  REFERENCES series(id)
);