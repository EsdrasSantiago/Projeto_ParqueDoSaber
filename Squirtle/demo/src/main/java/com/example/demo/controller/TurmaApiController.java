package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Turma;
import com.example.demo.repository.TurmaRepository;

@RestController
@RequestMapping("/api/turmas")
public class TurmaApiController {

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Turma> listar() {
        return turmaRepository.findAll();
    }

    @PostMapping
    public Turma salvar(@RequestBody Turma turma) {
        return turmaRepository.save(turma);
    }

    @PutMapping("/{id}")
    public Turma atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        Turma existente = turmaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        existente.setNome(turma.getNome());
        existente.setTurno(turma.getTurno());
        existente.setSala(turma.getSala());
        return turmaRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        turmaRepository.deleteById(id);
    }
}