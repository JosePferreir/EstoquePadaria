package com.example.padaria.service;

import com.example.padaria.DTO.RequestDTO.CompraDTO;
import com.example.padaria.DTO.RequestDTO.CompraMateriaPrimaDTO;
import com.example.padaria.DTO.RequestDTO.EstoqueMPDTO;
import com.example.padaria.Repository.CompraMPRepository;
import com.example.padaria.Repository.CompraRepository;
import com.example.padaria.model.Compra;
import com.example.padaria.model.CompraMP;
import com.example.padaria.model.CompraMateriaPrimaId;
import com.example.padaria.model.MateriaPrima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private CompraMPRepository compraMPRepository;

    public List<Compra> getAll() {
        List<Compra> compra = compraRepository.findAll();

        for(Compra c : compra) {
            c.setCompraMateriaPrimaList(compraMPRepository.findById_IdCompra(c.getId()));
        }

        return compra;
    }

    public Long saveCompra(List<EstoqueMPDTO> estoqueMP) {
        Float total = 0f;
        for( EstoqueMPDTO item : estoqueMP) {
            total += item.valor();
        }
        Compra c = compraRepository.save(new Compra(total,"MP", java.sql.Date.valueOf(LocalDate.now())));


        List<CompraMP> listaCompraMP = new ArrayList<>();
        for( EstoqueMPDTO item : estoqueMP) {
            CompraMP compraMP = new CompraMP();
            compraMP.setQuantidade(item.quantidade());
            compraMP.setValor(item.valor());
            MateriaPrima mp = new MateriaPrima(item.materiaPrima().getId(), item.materiaPrima().isAtivo(), item.materiaPrima().getDescricao(), item.materiaPrima().getUnidadeUtilizada(), item.materiaPrima().getUnidadeComprada());
            compraMP.setMateriaPrima(mp);

            CompraMateriaPrimaId id = new CompraMateriaPrimaId();
            id.setIdCompra(c.getId());
            id.setIdMateriaPrima(item.materiaPrima().getId());
            compraMP.setId(id);

            listaCompraMP.add(compraMP);
        }
        compraMPRepository.saveAll(listaCompraMP);
        return c.getId();
    }

}
