package com.example.padaria.model;
import com.example.padaria.DTO.RequestDTO.EstoqueMPDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "estoque_mp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueMP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mp", nullable = false)
    private MateriaPrima materiaPrima;

    @Column(name = "validade", nullable = false)
    private Date validade;

    @Column(name = "quantidade", nullable = false)
    private float quantidade;

    @Column(name = "quantidade_unidade", nullable = false)
    private Long quantidadeUnidade;

    @Column(name = "total_unidade_utilizada", nullable = false)
    private float totalUnidadeUtilizada;

    @Column(name = "valor", nullable = false)
    private float valor;

    @Column(name = "id_compra")
    private Long idCompra;

    // Construtor que aceita um EstoqueMPDTO
    public EstoqueMP(EstoqueMPDTO dto) {
        this.materiaPrima = dto.materiaPrima();
        this.validade = dto.validade();
        this.quantidade = dto.quantidade();
        this.quantidadeUnidade = dto.quantidadeUnidade();
        this.totalUnidadeUtilizada = dto.totalUnidadeUtilizada();
        this.valor = dto.valor();
    }
}
