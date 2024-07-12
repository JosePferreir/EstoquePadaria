package com.example.padaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "compra_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCompra {

    @EmbeddedId
    @JsonIgnore
    private ProdutoCompraId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produto", insertable = false, updatable = false)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Double quantidade;

    @Column(name = "valor", nullable = false)
    private Double valor;
}
