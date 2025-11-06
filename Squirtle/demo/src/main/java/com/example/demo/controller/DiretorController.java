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

import com.example.demo.model.Diretor;
import com.example.demo.repository.DiretorRepository;

@Controller
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorRepository repository;

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro() {
        return "diretores";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam String nome,
                            @RequestParam String email,
                            @RequestParam String telefone,
                            @RequestParam String senha) {
        Diretor diretor = new Diretor();
        diretor.setNome(nome);
        diretor.setEmail(email);
        diretor.setTelefone(telefone);
        diretor.setSenha(senha);
        repository.save(diretor);
        return "redirect:/diretores/lista";
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("diretores", repository.findAll());
        return "diretores-lista";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/diretores/lista";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Diretor diretor = repository.findById(id).orElseThrow();
        model.addAttribute("diretor", diretor);
        return "diretores-editar";
    }

    @PostMapping("/editar")
    public String editar(@RequestParam Long id,
                         @RequestParam String nome,
                         @RequestParam String email,
                         @RequestParam String telefone) {
        Diretor diretor = repository.findById(id).orElseThrow();
        diretor.setNome(nome);
        diretor.setEmail(email);
        diretor.setTelefone(telefone);
        repository.save(diretor);
        return "redirect:/diretores/lista";
    }
}