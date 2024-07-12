package com.example.padaria.DTO.Response;

import java.util.Date;

public record CompraResponseDTO (
        Long id,
        Float valorTotal,
        String tipoCompra,
        Date data
){
    public CompraResponseDTO(com.example.padaria.model.Compra compra) {
        this(compra.getId(), compra.getValorTotal(), compra.getTipoCompra(), compra.getData());
    }
}
