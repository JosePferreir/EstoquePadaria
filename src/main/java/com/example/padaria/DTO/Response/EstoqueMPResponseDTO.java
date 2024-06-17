package com.example.padaria.DTO.Response;

public record EstoqueMPResponseDTO(
        Long id,
        MateriaPrimaResponseDTO materiaPrima,
        String validade,
        float quantidade,
        Long quantidadeUnidade,
        float totalUnidadeUtilizada,
        float valor,
        Long idCompra) {
    public EstoqueMPResponseDTO(com.example.padaria.model.EstoqueMP estoqueMP) {
        this(estoqueMP.getId(), new MateriaPrimaResponseDTO(estoqueMP.getMateriaPrima()), estoqueMP.getValidade().toString(),
                estoqueMP.getQuantidade(), estoqueMP.getQuantidadeUnidade(), estoqueMP.getTotalUnidadeUtilizada(),
                estoqueMP.getValor(), estoqueMP.getIdCompra());
    }
}
