package com.example.padaria.Controller;

import com.example.padaria.DTO.RequestDTO.CompraDTO;
import com.example.padaria.DTO.RequestDTO.CompraMateriaPrimaDTO;
import com.example.padaria.Repository.CompraMPRepository;
import com.example.padaria.Repository.CompraRepository;
import com.example.padaria.model.Compra;
import com.example.padaria.model.CompraMP;
import com.example.padaria.model.CompraMateriaPrimaId;
import com.example.padaria.model.MateriaPrima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private CompraMPRepository compraMPRepository;

    @GetMapping("/mp/all")
    public List<Compra> getAll() {
        List<Compra> compra = compraRepository.findAll();

        for(Compra c : compra) {
            c.setCompraMateriaPrimaList(compraMPRepository.findById_IdCompra(c.getId()));
        }

        return compra;
    }

    @PostMapping("/mp/save")
    public void saveCompra(@RequestBody CompraDTO compra) {
        Compra c = compraRepository.save(new Compra(compra));
        System.out.println("O ID É: "+c.getId());
        List<CompraMP> CompraMP = new ArrayList<>();
        for(CompraMateriaPrimaDTO compraMateriaPrimaDTO : compra.compraMateriaPrimaList()) {
            CompraMP item = new CompraMP();
            item.setQuantidade(compraMateriaPrimaDTO.quantidade());
            item.setValor(compraMateriaPrimaDTO.valor());
            System.out.println("O ID DA MP É: "+compraMateriaPrimaDTO.materiaPrima().id());
            MateriaPrima mp = new MateriaPrima(compraMateriaPrimaDTO.materiaPrima().id(), compraMateriaPrimaDTO.materiaPrima().ativo(), compraMateriaPrimaDTO.materiaPrima().descricao(), compraMateriaPrimaDTO.materiaPrima().unidadeUtilizada(), compraMateriaPrimaDTO.materiaPrima().unidadeComprada());
            item.setMateriaPrima(mp);
            CompraMP.add(item);

            CompraMateriaPrimaId id = new CompraMateriaPrimaId();
            id.setIdCompra(c.getId());
            id.setIdMateriaPrima(compraMateriaPrimaDTO.materiaPrima().id());
            item.setId(id);
        }
        compraMPRepository.saveAll(CompraMP);
    }
}
