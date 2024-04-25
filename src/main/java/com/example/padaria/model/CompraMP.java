package com.example.padaria.model;

import com.example.padaria.DTO.RequestDTO.CompraMateriaPrimaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "compra_mp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraMP implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private CompraMateriaPrimaId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    @JsonIgnore
    private Compra compra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mp", insertable = false, updatable = false)
    private MateriaPrima materiaPrima;

    @Column(name = "quantidade")
    private Float quantidade;

    @Column(name = "valor")
    private Float valor;

}
