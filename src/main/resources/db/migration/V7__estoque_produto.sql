CREATE TABLE estoque_produto(
    id SERIAL PRIMARY KEY,
    id_produto INTEGER NOT NULL,
    id_compra INTEGER,
    validade DATE NOT NULL,
    quantidade FLOAT NOT NULL,
    data_criacao DATE,
    FOREIGN KEY (id_produto) REFERENCES produto(id),
    FOREIGN KEY (id_compra) REFERENCES compra(id)
);
