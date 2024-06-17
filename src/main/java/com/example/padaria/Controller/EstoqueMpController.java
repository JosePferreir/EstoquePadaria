package com.example.padaria.Controller;

import com.example.padaria.DTO.RequestDTO.EstoqueMPDTO;
import com.example.padaria.DTO.Response.EstoqueMPResponseDTO;
import com.example.padaria.Repository.EstoqueMPRepository;
import com.example.padaria.model.EstoqueMP;
import com.example.padaria.service.CompraService;
import com.example.padaria.service.EstoqueMPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque_mp")
public class EstoqueMpController {

    @Autowired
    private EstoqueMPRepository estoqueMPRepository;
    @Autowired
    private CompraService compraService;
    @Autowired
    private EstoqueMPService estoqueMPService;

    @PostMapping("/save")
    public void saveEstoqueMP(@RequestBody List<EstoqueMPDTO> estoqueMP) {
        estoqueMPService.saveEstoqueMP(estoqueMP);
    }
    @GetMapping("/all")
    public List<EstoqueMPResponseDTO> getAll() {
        List<EstoqueMPResponseDTO> estoqueMP = estoqueMPRepository.findAll().stream().map(EstoqueMPResponseDTO::new).toList();
        return estoqueMP;
    }
    @PutMapping("/update/{id}")
    public void updateEstoqueMP(@RequestBody EstoqueMPDTO mp, @PathVariable Long id) {
        estoqueMPService.updateEstoqueMP(mp, id);
    }
}
