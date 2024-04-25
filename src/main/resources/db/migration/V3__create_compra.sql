CREATE TABLE compra (
       id SERIAL PRIMARY KEY,
       valor_total FLOAT NOT NULL,
       tipo_compra VARCHAR(20) NOT NULL,
       data DATE NOT NULL
);

CREATE TABLE compra_mp(
       id_compra INTEGER NOT NULL,
       id_mp INTEGER NOT NULL,
       quantidade FLOAT NOT NULL,
       valor FLOAT NOT NULL,
       PRIMARY KEY (id_compra, id_mp),
       FOREIGN KEY (id_compra) REFERENCES compra(id),
       FOREIGN KEY (id_mp) REFERENCES materia_prima(id)
);
