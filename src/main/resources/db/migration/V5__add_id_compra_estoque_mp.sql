ALTER TABLE estoque_mp
    ADD id_compra INTEGER;

ALTER TABLE estoque_mp
    ADD CONSTRAINT fk_estoque_compra
        FOREIGN KEY (id_compra) REFERENCES compra(id);
