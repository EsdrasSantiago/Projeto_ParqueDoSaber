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

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;

@RestController
@RequestMapping("/api/alunos")
public class AlunoApiController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        return alunoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno existente = alunoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        existente.setNome(aluno.getNome());
        existente.setEmail(aluno.getEmail());
        existente.setTelefone(aluno.getTelefone());
        return alunoRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alunoRepository.deleteById(id);
    }
}