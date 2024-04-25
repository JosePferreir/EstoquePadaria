package com.example.padaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CompraMateriaPrimaId implements Serializable {

    @Column(name = "id_compra")
    private Long idCompra;

    @Column(name = "id_mp")
    private Long idMateriaPrima;

    // Getters and Setters
}
