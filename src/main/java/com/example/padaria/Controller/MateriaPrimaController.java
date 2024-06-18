package com.example.padaria.Controller;

import com.example.padaria.DTO.RequestDTO.MateriaPrimaDTO;
import com.example.padaria.DTO.Response.MateriaPrimaResponseDTO;
import com.example.padaria.Repository.MateriaPrimaRepository;
import com.example.padaria.model.MateriaPrima;
import com.example.padaria.service.MateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia_prima")
public class MateriaPrimaController {

    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

    @Autowired
    private MateriaPrimaService materiaPrimaService;


    @PostMapping("/save")
    public void saveMateriaPrimar(@RequestBody MateriaPrimaDTO mp) {
        materiaPrimaService.saveMateriaPrima(mp);
    }

    @GetMapping("/all")
    public List<MateriaPrimaResponseDTO> getAll() {
        return materiaPrimaService.getAll();
    }
    @PutMapping("/update/{id}")
    public void updateMateriaPrima(@RequestBody MateriaPrimaDTO mp, @PathVariable Long id) {
        materiaPrimaService.updateMateriaPrima(mp, id);
    }
    @PutMapping("/inactivate/{id}")
    public void inactivateActivateMateriaPrima(@PathVariable Long id) {
        materiaPrimaService.inactivateActivateMateriaPrima(id);
    }
    @GetMapping("/all/ativos")
    public List<MateriaPrimaResponseDTO> getAllActive() {
        return materiaPrimaService.getAllActive();
    }
}
