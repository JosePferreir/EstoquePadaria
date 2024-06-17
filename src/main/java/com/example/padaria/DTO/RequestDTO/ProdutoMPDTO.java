package com.example.padaria.DTO.RequestDTO;

import com.example.padaria.model.MateriaPrima;

public record ProdutoMPDTO(
        Long id,
        MateriaPrima materiaPrima,
        Float quantidade
) {
}
