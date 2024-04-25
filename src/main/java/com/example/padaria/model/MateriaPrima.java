package com.example.padaria.model;

import com.example.padaria.DTO.RequestDTO.MateriaPrimaRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "materia_prima")
@Entity(name = "materia_prima")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaPrima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "unidade_utilizada", nullable = false)
    private String unidadeUtilizada;

    @Column(name = "unidade_comprada", nullable = false)
    private String unidadeComprada;

    public MateriaPrima(MateriaPrimaRequestDTO mp) {
        this.ativo = mp.ativo();
        this.descricao = mp.descricao();
        this.unidadeUtilizada = mp.unidadeUtilizada();
        this.unidadeComprada = mp.unidadeComprada();
    }


}
