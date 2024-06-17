package com.example.padaria.Controller;


import com.example.padaria.DTO.RequestDTO.ProdutoDTO;
import com.example.padaria.DTO.Response.ProdutoResponseDTO;
import com.example.padaria.model.Produto;
import com.example.padaria.model.ProdutoMP;
import com.example.padaria.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/save")
    private void saveProduto(@RequestBody ProdutoDTO produto) {
        produtoService.saveProduto(produto);
    }

    @GetMapping("/all")
    public List<Produto> getAll() {
        return produtoService.getAll();
    }
    @GetMapping("/produtoMp/{id}")
    public List<ProdutoMP> getProdutoMp(@PathVariable Long id) {
        return produtoService.getProdutoMp(id);
    }

    @PutMapping("/update/{id}")
    public void updateProduto(@RequestBody ProdutoDTO produto, @PathVariable Long id) {
        produtoService.updateProduto(produto, id);
    }

    @PutMapping("/inactivate/{id}")
    public void inactivateActivateProduto(@PathVariable Long id) {
        produtoService.inactivateActivateProduto(id);
    }

    @GetMapping("/all/ativos")
    public List<ProdutoResponseDTO> getAllActive(){
        return produtoService.getAllActive();
    }

}
