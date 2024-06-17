package com.example.padaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoMpId implements Serializable {
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "id_mp")
    private Long idMp;
}
