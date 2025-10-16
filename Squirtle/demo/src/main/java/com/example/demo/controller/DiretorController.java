package com.example.demo.controller;

import com.example.demo.model.Diretor;
import com.example.demo.repository.DiretorRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    private final DiretorRepository repository;

    public DiretorController(DiretorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cadastrar")
    public Diretor cadastrar(Diretor diretor) {
        return repository.save(diretor);
    }
}
