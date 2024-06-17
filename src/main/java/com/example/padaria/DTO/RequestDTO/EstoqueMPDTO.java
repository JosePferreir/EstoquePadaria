package com.example.padaria.DTO.RequestDTO;


import com.example.padaria.model.EstoqueMP;
import com.example.padaria.model.MateriaPrima;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
public record EstoqueMPDTO(
        MateriaPrima materiaPrima,
        Date validade,
        float quantidade,
        Long quantidadeUnidade,
        float totalUnidadeUtilizada,
        float valor
) {}
