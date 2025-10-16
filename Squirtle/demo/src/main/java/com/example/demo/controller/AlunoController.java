package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository repository;

    public AlunoController(AlunoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cadastrar")
    public Aluno cadastrar(Aluno aluno) {
        return repository.save(aluno);
    }

    @GetMapping("/listar")
    public List<Aluno> listar() {
        return repository.findAll();
    }

    @GetMapping("/quantidade")
    public long quantidade() {
        return repository.count();
    }

    @GetMapping("/editar")
    public Aluno editar(@RequestParam Long id, @RequestParam String nome,
                        @RequestParam String email, @RequestParam String telefone) {
        Aluno aluno = repository.findById(id).orElseThrow();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
        return repository.save(aluno);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}