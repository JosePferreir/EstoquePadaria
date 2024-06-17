package com.example.padaria.Controller;

import com.example.padaria.DTO.RequestDTO.CompraDTO;
import com.example.padaria.DTO.RequestDTO.CompraMateriaPrimaDTO;
import com.example.padaria.Repository.CompraRepository;
import com.example.padaria.model.Compra;
import com.example.padaria.model.CompraMP;
import com.example.padaria.model.CompraMateriaPrimaId;
import com.example.padaria.model.MateriaPrima;
import com.example.padaria.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping("/mp/all")
    public List<Compra> getAll() {
        return compraService.getAll();
    }

    @PostMapping("/mp/save")
    public void saveCompra(@RequestBody CompraDTO compra) {
        //compraService.saveCompra(compra);
    }
}
