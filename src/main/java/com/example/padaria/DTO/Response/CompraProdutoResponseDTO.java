package com.example.padaria.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompraProdutoResponseDTO {
    private String produto;
    private String unidade;
    private Double quantidade;
    private Double valor;
    private Date validade;
}
