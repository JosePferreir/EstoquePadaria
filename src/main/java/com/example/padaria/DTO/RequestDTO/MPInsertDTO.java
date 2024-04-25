package com.example.padaria.DTO.RequestDTO;

public record MPInsertDTO(
        Long id,
        boolean ativo,
        String descricao,
        String unidadeUtilizada,
        String unidadeComprada
) {
}
