CREATE TABLE usuarios (
    id UUID PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    data_nascimento VARCHAR(10) NOT NULL,
    email VARCHAR(150) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,

    cartao_titular VARCHAR(100),
    cartao_numero VARCHAR(16),
    cartao_validade VARCHAR(10),
    cartao_codigo_seguranca VARCHAR(3)
);
