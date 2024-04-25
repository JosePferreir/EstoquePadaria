CREATE TABLE materia_prima (
            id SERIAL PRIMARY KEY,
            ativo BOOLEAN NOT NULL,
            descricao VARCHAR(255) NOT NULL,
            unidade_utilizada VARCHAR(50),
            unidade_comprada VARCHAR(50)
);
