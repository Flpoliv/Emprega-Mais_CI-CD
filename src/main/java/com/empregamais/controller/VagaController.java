package com.empregamais.controller;

import com.empregamais.model.Vaga;
import com.empregamais.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {
    @Autowired
    private VagaRepository repository;

    @GetMapping
    public List<Vaga> listar(Vaga vaga){
        return repository.findAll();
    }

    @PostMapping
    public Vaga criar(@RequestBody @Valid Vaga vaga){
        return  repository.save(vaga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> atualizar(@PathVariable @Valid Vaga vaga, @PathVariable Long id){
        return repository.findById(id)
                .map(v ->{
                    v.setTitulo(vaga.getTitulo());
                    v.setDescricao(vaga.getDescricao());
                    v.setEmpresa(vaga.getEmpresa());
                    return  ResponseEntity.ok(repository.save(v));
                } )
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(v -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
