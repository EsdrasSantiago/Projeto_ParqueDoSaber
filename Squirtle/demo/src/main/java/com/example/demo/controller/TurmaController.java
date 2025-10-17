package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Turma;
import com.example.demo.repository.TurmaRepository;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    // Exibe a página com a lista e os formulários (Thymeleaf)
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("turmas", repository.findAll());
        return "turmas"; 
    }

    // Cadastra uma nova turma (Thymeleaf)
    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam String nome,
                            @RequestParam String turno,
                            @RequestParam String sala) {
        Turma turma = new Turma();
        turma.setNome(nome);
        turma.setTurno(turno);
        turma.setSala(sala);
        repository.save(turma);
        return "redirect:/turmas";
    }

    // Edita uma turma existente (Thymeleaf)
    @PostMapping("/editar")
    public String editar(@RequestParam Long id,
                         @RequestParam String nome,
                         @RequestParam String turno,
                         @RequestParam String sala) {
        Turma turma = repository.findById(id).orElseThrow();
        turma.setNome(nome);
        turma.setTurno(turno);
        turma.setSala(sala);
        repository.save(turma);
        return "redirect:/turmas";
    }

    // Exclui uma turma (Thymeleaf)
    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/turmas";
    }

    // --- Rotas REST para HTML estático com JavaScript (AJAX) ---

    // Cadastrar via AJAX
    @PostMapping("/api/cadastrar")
    @ResponseBody
    public Turma cadastrarViaAjax(@RequestParam String nome,
                                   @RequestParam String turno,
                                   @RequestParam String sala) {
        Turma turma = new Turma();
        turma.setNome(nome);
        turma.setTurno(turno);
        turma.setSala(sala);
        return repository.save(turma);
    }

    // Listar via AJAX
    @GetMapping("/api/listar")
    @ResponseBody
    public List<Turma> listarJson() {
        return repository.findAll();
    }
}