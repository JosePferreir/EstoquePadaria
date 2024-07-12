package com.example.padaria.service;

import com.example.padaria.DTO.RequestDTO.EstoqueMPDTO;
import com.example.padaria.DTO.RequestDTO.EstoqueProdutoDTO;
import com.example.padaria.DTO.Response.CompraMPResponseDTO;
import com.example.padaria.DTO.Response.CompraProdutoResponseDTO;
import com.example.padaria.DTO.Response.CompraResponseDTO;
import com.example.padaria.Repository.*;
import com.example.padaria.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private CompraMPRepository compraMPRepository;
    @Autowired
    private ProdutoCompraRepository produtoCompraRepository;
    @Autowired
    private EstoqueMPRepository estoqueMPRepository;
    @Autowired
    private EstoqueProdutoRepository estoqueProdutoRepository;

    public List<CompraResponseDTO> getAll() {
        return compraRepository.findAll().stream().map(CompraResponseDTO::new).toList();
    }
    public List<CompraMPResponseDTO> getDetalhesMateriaPrima(Long id) {
        List<CompraMP> compraMP = compraMPRepository.findById_IdCompra(id);
        List<CompraMPResponseDTO> response = new ArrayList<>();
        for(CompraMP item : compraMP) {
            CompraMPResponseDTO dto = new CompraMPResponseDTO();
            dto.setMateriaPrima(item.getMateriaPrima().getDescricao());
            dto.setQuantidade(item.getQuantidade());
            dto.setValor(item.getValor());
            dto.setUnidade(item.getMateriaPrima().getUnidadeUtilizada());
            EstoqueMP emp = estoqueMPRepository.findByMateriaPrima_IdAndIdCompra(item.getMateriaPrima().getId(), id);
            dto.setValidade(emp.getValidade());
            dto.setQuantidadeUnidade(emp.getQuantidadeUnidade());
            response.add(dto);
        }
        return response;
    }

    public List<CompraProdutoResponseDTO> getDetalhesProduto(Long id) {
        List<ProdutoCompra> produtoCompra = produtoCompraRepository.findById_CompraId(id);
        List<CompraProdutoResponseDTO> response = new ArrayList<>();

        for (ProdutoCompra item : produtoCompra) {
            CompraProdutoResponseDTO dto = new CompraProdutoResponseDTO();
            dto.setProduto(item.getProduto().getNome());
            dto.setQuantidade(item.getQuantidade());
            dto.setValor(item.getValor());
            dto.setUnidade(item.getProduto().getUnidadeUtilizada());
            EstoqueProduto ep = estoqueProdutoRepository.findByProduto_IdAndIdCompra(item.getProduto().getId(), id);
            dto.setValidade(ep.getValidade());
            response.add(dto);
        }

        return response;
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

    @Transactional
    public Long saveCompraProduto(List<EstoqueProdutoDTO> estoqueList){
        Float total = 0f;
        for( EstoqueProdutoDTO item : estoqueList) {
            total += item.valor();
        }
        Compra c = compraRepository.save(new Compra(total,"Produto", java.sql.Date.valueOf(LocalDate.now())));
        System.out.println("id da compra: " + c.getId());
        List<ProdutoCompra> listaEstoqueProduto = new ArrayList<>();
        for( EstoqueProdutoDTO item : estoqueList) {
            ProdutoCompra produtoCompra = new ProdutoCompra();
            produtoCompra.setQuantidade(Double.valueOf(item.quantidade()));
            produtoCompra.setValor(Double.valueOf(item.valor()));
            Produto p = new Produto(item.produto().id(),item.produto().nome(),item.produto().unidadeUtilizada(),item.produto().ativo());
            produtoCompra.setProduto(p);

            ProdutoCompraId id = new ProdutoCompraId();
            id.setCompraId(c.getId());
            id.setProdutoId(item.produto().id());
            produtoCompra.setId(id);

            listaEstoqueProduto.add(produtoCompra);
        }
        produtoCompraRepository.saveAll(listaEstoqueProduto);
        return c.getId();
    }

}
