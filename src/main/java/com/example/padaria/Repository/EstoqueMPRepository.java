package com.example.padaria.Repository;

import com.example.padaria.model.EstoqueMP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueMPRepository extends JpaRepository<EstoqueMP, Long> {

    List<EstoqueMP> findByMateriaPrima_Id(Long idMateriaPrima);

}
