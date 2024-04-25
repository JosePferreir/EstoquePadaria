package com.example.padaria.DTO.RequestDTO;

public record CompraMateriaPrimaDTO(
        MPInsertDTO materiaPrima,
        Float quantidade,
        Float valor) {
}
