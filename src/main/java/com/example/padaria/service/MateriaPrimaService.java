package com.example.padaria.service;

import com.example.padaria.DTO.RequestDTO.MateriaPrimaDTO;
import com.example.padaria.DTO.Response.MateriaPrimaResponseDTO;
import com.example.padaria.Repository.MateriaPrimaRepository;
import com.example.padaria.model.MateriaPrima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MateriaPrimaService {

    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

    public void saveMateriaPrima(MateriaPrimaDTO mp) {
        System.out.println(new MateriaPrima(mp));
        materiaPrimaRepository.save(new MateriaPrima(mp));
    }

    public List<MateriaPrimaResponseDTO> getAll() {
        List<MateriaPrimaResponseDTO> materiaPrima = materiaPrimaRepository.findAll().stream().map(MateriaPrimaResponseDTO::new).toList();
        return materiaPrima;
    }

    public void updateMateriaPrima( MateriaPrimaDTO mp, Long id) {
        MateriaPrima materiaPrima = materiaPrimaRepository.findById(id).orElseThrow();
        materiaPrima.setAtivo(mp.ativo());
        materiaPrima.setDescricao(mp.descricao());
        materiaPrima.setUnidadeUtilizada(mp.unidadeUtilizada());
        materiaPrima.setUnidadeComprada(mp.unidadeComprada());
        materiaPrimaRepository.save(materiaPrima);
    }

    public List<MateriaPrimaResponseDTO> getAllActive() {
        List<MateriaPrimaResponseDTO> materiaPrima = materiaPrimaRepository.findAllByAtivoTrue().stream().map(MateriaPrimaResponseDTO::new).toList();
        return materiaPrima;
    }

    public void inactivateActivateMateriaPrima( Long id) {
        MateriaPrima materiaPrima = materiaPrimaRepository.findById(id).orElseThrow();
        materiaPrima.setAtivo(!materiaPrima.isAtivo());
        materiaPrimaRepository.save(materiaPrima);
    }
}
