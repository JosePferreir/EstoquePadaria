package com.example.padaria.Repository;

import com.example.padaria.model.CompraMP;
import com.example.padaria.model.CompraMateriaPrimaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraMPRepository extends JpaRepository<CompraMP, CompraMateriaPrimaId> {
    List<CompraMP> findById_IdCompra(Long idCompra);
}
