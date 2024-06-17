package com.example.padaria.Repository;

import com.example.padaria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p LEFT JOIN FETCH p.produtoMpList")
    List<Produto> findAllWithProdutoMpList();

    Collection<Produto> findAllByAtivoTrue();
}
