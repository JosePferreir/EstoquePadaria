package com.example.padaria.DTO.Response;

import com.example.padaria.model.MateriaPrima;

public record MateriaPrimaResponseDTO(Long id,
                                      boolean ativo,
                                      String descricao,
                                      String unidadeUtilizada,
                                      String unidadeComprada) {
    public MateriaPrimaResponseDTO(MateriaPrima mp) {
        this(mp.getId(), mp.isAtivo(), mp.getDescricao(), mp.getUnidadeUtilizada(),
                mp.getUnidadeComprada());
    }
}
