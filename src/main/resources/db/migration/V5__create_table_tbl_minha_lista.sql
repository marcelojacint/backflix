CREATE TABLE tbl_minha_lista (
    id_minha_lista BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    id_filme UUID,
    id_serie UUID,
    id_usuario UUID NOT NULL,

    CONSTRAINT fk_lista_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    CONSTRAINT fk_lista_filme   FOREIGN KEY (id_filme) REFERENCES filmes(id),
    CONSTRAINT fk_lista_serie   FOREIGN KEY (id_serie) REFERENCES series(id)
);