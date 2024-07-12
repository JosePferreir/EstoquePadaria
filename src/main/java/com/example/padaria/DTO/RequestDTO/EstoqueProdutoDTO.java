package com.example.padaria.DTO.RequestDTO;

import java.util.Date;
import java.util.Optional;

public record EstoqueProdutoDTO(
        Long id,
        ProdutoDTO produto,
        Date validade,
        Long quantidade,
        Date dataCriacao,
        Float valor
) {
}
