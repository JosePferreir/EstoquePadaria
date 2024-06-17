package com.example.padaria.model;

import com.example.padaria.DTO.RequestDTO.CompraDTO;
import com.example.padaria.DTO.RequestDTO.CompraMateriaPrimaDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_total", nullable = false)
    private Float valorTotal;

    @Column(name = "tipo_compra", nullable = false)
    private String tipoCompra;

    @Column(name = "data", nullable = false)
    private Date data;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompraMP> compraMateriaPrimaList;

    public Compra(CompraDTO compraDTO) {
        this.valorTotal = compraDTO.valorTotal();
        this.tipoCompra = compraDTO.tipoCompra();
        this.data = compraDTO.data();
    }

    public Compra(Float valorTotal, String tipoCompra, Date data) {
        this.valorTotal = valorTotal;
        this.tipoCompra = tipoCompra;
        this.data = data;
    }
}
