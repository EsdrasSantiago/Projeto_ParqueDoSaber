package com.example.demo.controller;

import com.example.demo.model.Diretor;
import com.example.demo.repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diretores")
public class DiretorApiController {

    @Autowired
    private DiretorRepository diretorRepository;

    @GetMapping
    public List<Diretor> listar() {
        return diretorRepository.findAll();
    }

    @PostMapping
    public Diretor salvar(@RequestBody Diretor diretor) {
        return diretorRepository.save(diretor);
    }

    @PutMapping("/{id}")
    public Diretor atualizar(@PathVariable Long id, @RequestBody Diretor diretor) {
        Diretor existente = diretorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diretor não encontrado"));
        existente.setNome(diretor.getNome());
        existente.setEmail(diretor.getEmail());
        existente.setTelefone(diretor.getTelefone());
        return diretorRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        diretorRepository.deleteById(id);
    }
}