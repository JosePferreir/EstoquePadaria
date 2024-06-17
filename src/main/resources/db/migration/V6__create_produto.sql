CREATE TABLE produto (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        unidade_utilizada VARCHAR(30) NOT NULL,
        ativo BOOLEAN NOT NULL
);

CREATE TABLE produto_mp(
        id_produto INTEGER NOT NULL,
        id_mp INTEGER NOT NULL,
        quantidade FLOAT NOT NULL,
        PRIMARY KEY (id_produto, id_mp),
        FOREIGN KEY (id_produto) REFERENCES produto(id),
        FOREIGN KEY (id_mp) REFERENCES materia_prima(id)
);
