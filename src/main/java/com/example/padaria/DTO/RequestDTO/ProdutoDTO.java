package com.example.padaria.DTO.RequestDTO;

import com.example.padaria.DTO.Response.MateriaPrimaResponseDTO;

import java.util.List;
import java.util.Optional;

public record ProdutoDTO (
    Long id,
    String nome,
    String unidadeUtilizada,
    boolean ativo,
    List<ProdutoMPDTO> produtoMp
){}
