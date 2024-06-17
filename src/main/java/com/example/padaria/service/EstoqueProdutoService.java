package com.example.padaria.service;


import com.example.padaria.DTO.RequestDTO.EstoqueProdutoDTO;
import com.example.padaria.DTO.Response.EstoqueProdutoResponseDTO;
import com.example.padaria.Repository.EstoqueMPRepository;
import com.example.padaria.Repository.EstoqueProdutoRepository;
import com.example.padaria.Repository.ProdutoMPRepository;
import com.example.padaria.exception.MateriaPrimaExpiredException;
import com.example.padaria.exception.MateriaPrimaInsufficientException;
import com.example.padaria.exception.MateriaPrimaNotFoundException;
import com.example.padaria.model.EstoqueMP;
import com.example.padaria.model.EstoqueProduto;
import com.example.padaria.model.ProdutoMP;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueProdutoService {

    @Autowired
    private EstoqueProdutoRepository estoqueProdutoRepository;

    @Autowired
    private ProdutoMPRepository produtoMPRepository;
    @Autowired
    private EstoqueMPRepository estoqueMPRepository;

    @Transactional
    public ResponseEntity<?> saveEstoqueProduto(EstoqueProdutoDTO ep) {
        List<ProdutoMP> produtoMPList = produtoMPRepository.findById_IdProduto(ep.produto().id());
        List<EstoqueMP> mpUtilizada = new ArrayList<>();
        System.out.println(ep);
        Date currentDate = new Date();

        for (ProdutoMP p : produtoMPList) {
            List<EstoqueMP> empList = estoqueMPRepository.findByMateriaPrima_Id(p.getMateriaPrima().getId()).stream()
                    .sorted((emp1, emp2) -> emp1.getValidade().compareTo(emp2.getValidade()))
                    .collect(Collectors.toList());

            if (empList.isEmpty()) {
                throw new MateriaPrimaNotFoundException("Matéria-prima " + p.getMateriaPrima().getDescricao() +
                        " não encontrada no estoque.");
            }

            float totalQuantidadeDisponivel = 0;
            for (EstoqueMP emp : empList) {
                // Verificar se a data de validade é posterior à data atual
                if (emp.getValidade().before(currentDate) && emp.getQuantidade() > 0) {
                    throw new MateriaPrimaExpiredException("Matéria-prima " + p.getMateriaPrima().getDescricao() +
                            " está vencida.");
                }
                System.out.println("Validade: " + emp.getValidade());
                totalQuantidadeDisponivel += emp.getTotalUnidadeUtilizada();
            }

            System.out.println(totalQuantidadeDisponivel);
            System.out.println(p.getQuantidade()* ep.quantidade());

            // Verificar se a quantidade disponível é suficiente
            if (totalQuantidadeDisponivel < p.getQuantidade() * ep.quantidade()) {
                throw new MateriaPrimaInsufficientException("Quantidade insuficiente de matéria-prima " +
                        p.getMateriaPrima().getDescricao() + " no estoque.");
            }

            float quantidadeNecessaria = p.getQuantidade() * ep.quantidade();
            for (EstoqueMP emp : empList) {
                if (quantidadeNecessaria <= 0) {
                    break;
                }

                if (emp.getTotalUnidadeUtilizada() <= quantidadeNecessaria) {
                    quantidadeNecessaria -= emp.getTotalUnidadeUtilizada();
                    emp.setTotalUnidadeUtilizada(0);
                    emp.setQuantidade(0);
                } else {
                    emp.setTotalUnidadeUtilizada(emp.getTotalUnidadeUtilizada() - quantidadeNecessaria);
                    emp.setQuantidade(emp.getTotalUnidadeUtilizada() / emp.getQuantidadeUnidade());
                    quantidadeNecessaria = 0;
                }
                mpUtilizada.add(emp);
            }
        }

        // Salvar o estoque da matéria-prima utilizada
        estoqueMPRepository.saveAll(mpUtilizada);

        System.out.println("Data vencimento: " + ep.validade());
        System.out.println("Data criação: " + ep.dataCriacao());
        // Salvar o estoque do produto
        estoqueProdutoRepository.save(new EstoqueProduto(ep));

        // Exemplo de resposta de sucesso
        return ResponseEntity.ok("Estoque do produto salvo com sucesso.");
    }

    public List<EstoqueProdutoResponseDTO> getAll() {
        return estoqueProdutoRepository.findAll().stream().map(EstoqueProdutoResponseDTO::new).toList();
    }
}
