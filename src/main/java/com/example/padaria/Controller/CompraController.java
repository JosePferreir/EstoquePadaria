package com.example.padaria.Controller;

import com.example.padaria.DTO.RequestDTO.CompraDTO;
import com.example.padaria.DTO.Response.CompraMPResponseDTO;
import com.example.padaria.DTO.Response.CompraProdutoResponseDTO;
import com.example.padaria.DTO.Response.CompraResponseDTO;
import com.example.padaria.model.Compra;
import com.example.padaria.model.CompraMP;
import com.example.padaria.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping("/mp/detalhes/{id}")
    public List<CompraMPResponseDTO> getDetalhesMateriaPrima(@PathVariable Long id) {
        return compraService.getDetalhesMateriaPrima(id);
    }
    @GetMapping("/all")
    public List<CompraResponseDTO> getAll() {
        return compraService.getAll();
    }
    @GetMapping("/produto/detalhes/{id}")
    public List<CompraProdutoResponseDTO> getDetalhesProduto(@PathVariable Long id) {
        return compraService.getDetalhesProduto(id);
    }
}
