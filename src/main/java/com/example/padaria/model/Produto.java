package com.example.padaria.model;

import com.example.padaria.DTO.RequestDTO.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "unidade_utilizada", nullable = false)
    private String unidadeUtilizada;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProdutoMP> produtoMpList;

    public Produto(ProdutoDTO p) {
        this.nome = p.nome();
        this.unidadeUtilizada = p.unidadeUtilizada();
        this.ativo = p.ativo();
    }
}
