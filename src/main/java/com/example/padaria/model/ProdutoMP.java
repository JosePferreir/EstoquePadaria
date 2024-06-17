package com.example.padaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "produto_mp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoMP implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private ProdutoMpId id;

    @Column(name = "quantidade", nullable = false)
    private Float quantidade;

    @MapsId("idProduto")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produto", insertable = false, updatable = false)
    private Produto produto;

    @MapsId("idMp")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mp", insertable = false, updatable = false)
    private MateriaPrima materiaPrima;

    @Override
    public String toString() {
        return "ProdutoMP{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", produto=" + produto +
                ", materiaPrima=" + materiaPrima +
                '}';
    }
}
