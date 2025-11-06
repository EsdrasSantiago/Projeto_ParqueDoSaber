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

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro() {
        return "turmas"; 
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam String nome,
                            @RequestParam String turno,
                            @RequestParam String sala) {
        Turma turma = new Turma();
        turma.setNome(nome);
        turma.setTurno(turno);
        turma.setSala(sala);
        repository.save(turma);
        return "redirect:/turmas/lista"; 
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("turmas", repository.findAll());
        return "turmas-lista";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/turmas/lista";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Turma turma = repository.findById(id).orElseThrow();
        model.addAttribute("turma", turma);
        return "turmas-editar";
    }

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
        return "redirect:/turmas/lista";
    }
}