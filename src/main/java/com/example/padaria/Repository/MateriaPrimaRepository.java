package com.example.padaria.Repository;

import com.example.padaria.model.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Long> {

    Collection<MateriaPrima> findAllByAtivoTrue();
}
