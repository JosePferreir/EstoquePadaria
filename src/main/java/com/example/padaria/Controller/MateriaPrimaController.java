package com.example.padaria.Controller;

import com.example.padaria.DTO.RequestDTO.MateriaPrimaRequestDTO;
import com.example.padaria.DTO.Response.MateriaPrimaResponseDTO;
import com.example.padaria.Repository.MateriaPrimaRepository;
import com.example.padaria.model.MateriaPrima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia_prima")
public class MateriaPrimaController {

    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;


    @PostMapping("/save")
    public void saveMateriaPrimar(@RequestBody MateriaPrimaRequestDTO mp) {
        materiaPrimaRepository.save(new MateriaPrima(mp));
    }

    @GetMapping("/all")
    public List<MateriaPrimaResponseDTO> getAll() {
        List<MateriaPrimaResponseDTO> materiaPrima = materiaPrimaRepository.findAll().stream().map(MateriaPrimaResponseDTO::new).toList();
        return materiaPrima;
    }
}
