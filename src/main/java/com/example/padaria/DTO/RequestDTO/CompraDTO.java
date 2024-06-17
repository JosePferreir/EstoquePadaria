package com.example.padaria.DTO.RequestDTO;

import jakarta.annotation.Nullable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public record CompraDTO(
        Optional<Integer> id,
        Float valorTotal,
        String tipoCompra,
        Date data,
        List<CompraMateriaPrimaDTO> compraMateriaPrimaList) {
}
