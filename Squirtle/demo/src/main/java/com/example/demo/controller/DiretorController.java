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

    // Exibe a página com a lista e os formulários (Thymeleaf)
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("diretores", repository.findAll());
        return "diretores"; 
    }

    // Cadastra um novo diretor (Thymeleaf)
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
        return "redirect:/diretores";
    }

    // Edita um diretor existente (Thymeleaf)
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
        return "redirect:/diretores";
    }

    // Exclui um diretor (Thymeleaf)
    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/diretores";
    }

    // --- Rotas REST para HTML estático com JavaScript (AJAX) ---

    // Cadastrar via AJAX
    @PostMapping("/api/cadastrar")
    @ResponseBody
    public Diretor cadastrarViaAjax(@RequestParam String nome,
                                     @RequestParam String email,
                                     @RequestParam String telefone,
                                     @RequestParam String senha) {
        Diretor diretor = new Diretor();
        diretor.setNome(nome);
        diretor.setEmail(email);
        diretor.setTelefone(telefone);
        diretor.setSenha(senha);
        return repository.save(diretor);
    }

    // Listar via AJAX
    @GetMapping("/api/listar")
    @ResponseBody
    public List<Diretor> listarJson() {
        return repository.findAll();
    }
}