CREATE TABLE compra_produto(
    id_compra INTEGER NOT NULL,
    id_produto INTEGER NOT NULL,
    quantidade FLOAT NOT NULL,
    valor FLOAT NOT NULL,
    PRIMARY KEY (id_compra, id_produto),
    FOREIGN KEY (id_compra) REFERENCES compra(id),
    FOREIGN KEY (id_produto) REFERENCES produto(id)
);
