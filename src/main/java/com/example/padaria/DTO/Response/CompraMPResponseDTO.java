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
public class CompraMPResponseDTO {
    private String materiaPrima;
    private String unidade;
    private Long quantidadeUnidade;
    private float quantidade;
    private Date validade;
    private float valor;
}
