package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Turma;
import com.example.demo.repository.TurmaRepository;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaRepository repository;

    public TurmaController(TurmaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cadastrar")
    public Turma cadastrar(Turma turma) {
        return repository.save(turma);
    }

    @GetMapping("/listar")
    public List<Turma> listar() {
        return repository.findAll();
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
