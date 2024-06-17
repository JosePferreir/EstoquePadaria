package com.example.padaria.DTO.Response;

import com.example.padaria.model.Produto;

public record ProdutoResponseDTO(
        Long id,
        boolean ativo,
        String nome,
        String unidadeUtilizada
) {
    public ProdutoResponseDTO(Produto p){
        this(p.getId(),p.isAtivo(),p.getNome(),p.getUnidadeUtilizada());
    }
}
