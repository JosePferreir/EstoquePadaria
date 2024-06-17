CREATE TABLE estoque_mp(
    id SERIAL PRIMARY KEY,
    id_mp INTEGER NOT NULL,
    validade DATE NOT NULL,
    quantidade FLOAT NOT NULL,
    quantidade_unidade FLOAT NOT NULL,
    total_unidade_utilizada FLOAT NOT NULL,
    valor FLOAT NOT NULL DEFAULT 0,
    FOREIGN KEY (id_mp) REFERENCES materia_prima(id)
);
