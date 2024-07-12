package com.example.padaria.DTO.Response;

import java.util.Date;
import java.util.Optional;

public record EstoqueProdutoResponseDTO(
        Long id,
        ProdutoResponseDTO produto,
        Date validade,
        Date dataCriacao,
        float quantidade,
        Long idCompra
) {
    public EstoqueProdutoResponseDTO(com.example.padaria.model.EstoqueProduto estoqueProduto) {
        this(estoqueProduto.getId(), new ProdutoResponseDTO(estoqueProduto.getProduto()), estoqueProduto.getValidade(),
                estoqueProduto.getDataCriacao(), estoqueProduto.getQuantidade(), estoqueProduto.getIdCompra());
    }
}
