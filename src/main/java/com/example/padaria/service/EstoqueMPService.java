package com.example.padaria.service;

import com.example.padaria.DTO.RequestDTO.CompraDTO;
import com.example.padaria.DTO.RequestDTO.CompraMateriaPrimaDTO;
import com.example.padaria.DTO.RequestDTO.EstoqueMPDTO;
import com.example.padaria.Repository.CompraRepository;
import com.example.padaria.Repository.EstoqueMPRepository;
import com.example.padaria.model.Compra;
import com.example.padaria.model.CompraMP;
import com.example.padaria.model.EstoqueMP;
import com.example.padaria.model.MateriaPrima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EstoqueMPService {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private EstoqueMPRepository estoqueMPRepository;
    @Autowired
    private CompraService compraService;

    public void saveEstoqueMP(List<EstoqueMPDTO> estoqueMP) {
        Long compra_id = compraService.saveCompra(estoqueMP);
        for ( EstoqueMPDTO item : estoqueMP) {
            EstoqueMP estoque = new EstoqueMP(item);
            estoque.setIdCompra(compra_id);
            estoque.setTotalUnidadeUtilizada(item.quantidade() * item.quantidadeUnidade());
            estoqueMPRepository.save(estoque);
        }
    }

    public void updateEstoqueMP(EstoqueMPDTO mp, Long id){
        EstoqueMP estoqueMP = estoqueMPRepository.findById(id).orElseThrow();

        boolean quantidadeModificada = mp.quantidade() != estoqueMP.getQuantidade();
        boolean totalModificado = mp.totalUnidadeUtilizada() != estoqueMP.getTotalUnidadeUtilizada();
        boolean quantidadeUnidadeModificada = mp.quantidadeUnidade() != estoqueMP.getQuantidadeUnidade();

        if (quantidadeModificada || quantidadeUnidadeModificada) {
            estoqueMP.setQuantidade(mp.quantidade());
            estoqueMP.setQuantidadeUnidade(mp.quantidadeUnidade());
            estoqueMP.setTotalUnidadeUtilizada(mp.quantidade() * mp.quantidadeUnidade());
        } else if (totalModificado) {
            estoqueMP.setTotalUnidadeUtilizada(mp.totalUnidadeUtilizada());
            estoqueMP.setQuantidade(mp.totalUnidadeUtilizada() / mp.quantidadeUnidade());
        } else {
            estoqueMP.setQuantidade(mp.quantidade());
            estoqueMP.setQuantidadeUnidade(mp.quantidadeUnidade());
            estoqueMP.setTotalUnidadeUtilizada(mp.totalUnidadeUtilizada());
        }

        estoqueMP.setMateriaPrima(mp.materiaPrima());
        estoqueMP.setValidade(mp.validade());
        estoqueMP.setValor(mp.valor());

        estoqueMPRepository.save(estoqueMP);

    }
}
