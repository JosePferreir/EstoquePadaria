package com.example.padaria.DTO.RequestDTO;

import java.util.Date;
import java.util.List;

public record CompraDTO(
        int id,
        Float valorTotal,
        String tipoCompra,
        Date data,
        List<CompraMateriaPrimaDTO> compraMateriaPrimaList) {
}
