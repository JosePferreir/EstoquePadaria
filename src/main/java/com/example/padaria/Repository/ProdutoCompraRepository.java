package com.example.padaria.Repository;

import com.example.padaria.model.ProdutoCompra;
import com.example.padaria.model.ProdutoCompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoCompraRepository extends JpaRepository<ProdutoCompra, ProdutoCompraId> {
    List<ProdutoCompra> findById_CompraId(Long idCompra);
}
