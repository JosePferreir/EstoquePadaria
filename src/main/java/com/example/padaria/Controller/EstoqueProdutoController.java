package com.example.padaria.Controller;

import com.example.padaria.DTO.RequestDTO.EstoqueMPDTO;
import com.example.padaria.DTO.RequestDTO.EstoqueProdutoDTO;
import com.example.padaria.DTO.Response.EstoqueProdutoResponseDTO;
import com.example.padaria.service.EstoqueProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque_produto")
public class EstoqueProdutoController {

    @Autowired
    private EstoqueProdutoService estoqueProdutoService;

    @PostMapping("/save")
    public ResponseEntity<?> saveEstoqueProduto(@RequestBody EstoqueProdutoDTO estoqueProdutoDTO) {
        return estoqueProdutoService.saveEstoqueProduto(estoqueProdutoDTO);
    }

    @GetMapping("/all")
    public List<EstoqueProdutoResponseDTO> getAll() {
        return estoqueProdutoService.getAll();
    }

    @PutMapping("/update/{id}")
    public void updateEstoqueProduto(@RequestBody EstoqueProdutoDTO estoqueProdutoDTO, @PathVariable Long id) {
        estoqueProdutoService.updateEstoqueProduto(estoqueProdutoDTO, id);
    }

    @PostMapping("/save_compra")
    public void saveCompraProduto(@RequestBody List<EstoqueProdutoDTO> estoqueList) {
        estoqueProdutoService.saveCompraProduto(estoqueList);
    }


}
