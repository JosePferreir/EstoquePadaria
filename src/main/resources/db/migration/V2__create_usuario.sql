CREATE TABLE usuario (
                         id SERIAL PRIMARY KEY,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         nome VARCHAR(255) NOT NULL
);
