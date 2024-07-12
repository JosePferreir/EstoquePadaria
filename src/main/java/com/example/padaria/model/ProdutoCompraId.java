package com.example.padaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Embeddable
public class ProdutoCompraId implements Serializable {

    @Column(name = "id_compra")
    private Long compraId;

    @Column(name = "id_produto")
    private Long produtoId;

}
