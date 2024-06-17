package com.example.padaria.service;

import com.example.padaria.DTO.RequestDTO.ProdutoDTO;
import com.example.padaria.DTO.RequestDTO.ProdutoMPDTO;
import com.example.padaria.DTO.Response.ProdutoResponseDTO;
import com.example.padaria.Repository.ProdutoMPRepository;
import com.example.padaria.Repository.ProdutoRepository;
import com.example.padaria.model.MateriaPrima;
import com.example.padaria.model.Produto;
import com.example.padaria.model.ProdutoMP;
import com.example.padaria.model.ProdutoMpId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoMPRepository produtoMPRepository;

    public void saveProduto(ProdutoDTO produto) {
        Produto p = produtoRepository.save(new Produto(produto));
        List<ProdutoMP> pmp = new ArrayList<>();

        for(ProdutoMPDTO ingrediente : produto.produtoMp()){
            ProdutoMP produtoMP = new ProdutoMP();
            produtoMP.setProduto(p);
            MateriaPrima mp = new MateriaPrima(ingrediente.materiaPrima().getId(), ingrediente.materiaPrima().isAtivo(), ingrediente.materiaPrima().getDescricao(), ingrediente.materiaPrima().getUnidadeUtilizada(), ingrediente.materiaPrima().getUnidadeComprada());
            produtoMP.setQuantidade(ingrediente.quantidade());
            produtoMP.setMateriaPrima(mp);

            ProdutoMpId id = new ProdutoMpId();
            id.setIdProduto(p.getId());
            id.setIdMp(ingrediente.materiaPrima().getId());
            produtoMP.setId(id);

            pmp.add(produtoMP);
        }
        produtoMPRepository.saveAll(pmp);
    }

public List<Produto> getAll() {
        return produtoRepository.findAllWithProdutoMpList();
    }

    public List<ProdutoMP> getProdutoMp(Long id) {
        return produtoMPRepository.findById_IdProduto(id);
    }

    public void updateProduto(ProdutoDTO produto, Long id) {
        Produto p = produtoRepository.findById(id).get();
        p.setNome(produto.nome());
        p.setUnidadeUtilizada(produto.unidadeUtilizada());
        p.setAtivo(produto.ativo());
        p = produtoRepository.save(p);

        List<ProdutoMP> pmp = produtoMPRepository.findById_IdProduto(id);
        produtoMPRepository.deleteAll(pmp);

        List<ProdutoMP> pmpNew = new ArrayList<>();
        for(ProdutoMPDTO ingrediente : produto.produtoMp()){
            ProdutoMP produtoMP = new ProdutoMP();
            produtoMP.setProduto(p);
            MateriaPrima mp = new MateriaPrima(ingrediente.materiaPrima().getId(), ingrediente.materiaPrima().isAtivo(), ingrediente.materiaPrima().getDescricao(), ingrediente.materiaPrima().getUnidadeUtilizada(), ingrediente.materiaPrima().getUnidadeComprada());
            produtoMP.setQuantidade(ingrediente.quantidade());
            produtoMP.setMateriaPrima(mp);

            ProdutoMpId idMp = new ProdutoMpId();
            idMp.setIdProduto(p.getId());
            idMp.setIdMp(ingrediente.materiaPrima().getId());
            produtoMP.setId(idMp);

            pmpNew.add(produtoMP);
        }
        produtoMPRepository.saveAll(pmpNew);
    }

    public void inactivateActivateProduto(Long id) {
        Produto p = produtoRepository.findById(id).get();
        p.setAtivo(!p.isAtivo());
        produtoRepository.save(p);
    }

    public List<ProdutoResponseDTO> getAllActive() {
        return produtoRepository.findAllByAtivoTrue().stream().map(ProdutoResponseDTO::new).toList();
    }
}
