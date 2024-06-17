package com.example.padaria.Repository;

import com.example.padaria.model.ProdutoMP;
import com.example.padaria.model.ProdutoMpId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoMPRepository extends JpaRepository<ProdutoMP, ProdutoMpId>{
    List<ProdutoMP> findById_IdProduto(Long idProduto);
}
