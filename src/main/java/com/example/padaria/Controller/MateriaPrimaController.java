package com.example.padaria.Controller;

import com.example.padaria.DTO.RequestDTO.MateriaPrimaDTO;
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
    public void saveMateriaPrimar(@RequestBody MateriaPrimaDTO mp) {
        System.out.println(new MateriaPrima(mp));
        materiaPrimaRepository.save(new MateriaPrima(mp));
    }

    @GetMapping("/all")
    public List<MateriaPrimaResponseDTO> getAll() {
        List<MateriaPrimaResponseDTO> materiaPrima = materiaPrimaRepository.findAll().stream().map(MateriaPrimaResponseDTO::new).toList();
        return materiaPrima;
    }
    @PutMapping("/update/{id}")
    public void updateMateriaPrima(@RequestBody MateriaPrimaDTO mp, @PathVariable Long id) {
        MateriaPrima materiaPrima = materiaPrimaRepository.findById(id).orElseThrow();
        materiaPrima.setAtivo(mp.ativo());
        materiaPrima.setDescricao(mp.descricao());
        materiaPrima.setUnidadeUtilizada(mp.unidadeUtilizada());
        materiaPrima.setUnidadeComprada(mp.unidadeComprada());
        materiaPrimaRepository.save(materiaPrima);
    }
    @PutMapping("/inactivate/{id}")
    public void inactivateMateriaPrima(@PathVariable Long id) {
        MateriaPrima materiaPrima = materiaPrimaRepository.findById(id).orElseThrow();
        materiaPrima.setAtivo(false);
        materiaPrimaRepository.save(materiaPrima);
    }
    @GetMapping("/all/ativos")
    public List<MateriaPrimaResponseDTO> getAllActive() {
        List<MateriaPrimaResponseDTO> materiaPrima = materiaPrimaRepository.findAllByAtivoTrue().stream().map(MateriaPrimaResponseDTO::new).toList();
        return materiaPrima;
    }
}
