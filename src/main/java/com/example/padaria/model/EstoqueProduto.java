package com.example.padaria.model;

import com.example.padaria.DTO.RequestDTO.EstoqueProdutoDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "estoque_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;


    @Column(name = "validade", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date validade;

    @Column(name = "quantidade", nullable = false)
    private Long quantidade;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Nullable
    @Column(name = "valor")
    private Float valor;

    @Nullable
    @Column(name = "id_compra")
    private Long idCompra;

    public EstoqueProduto(EstoqueProdutoDTO ep) {
        this.id = ep.id();
        this.produto = new Produto(ep.produto());
        this.produto.setId(ep.produto().id());
        this.validade = ep.validade();
        this.quantidade = ep.quantidade();
        this.dataCriacao = ep.dataCriacao();
        this.valor = ep.valor();
    }


}

