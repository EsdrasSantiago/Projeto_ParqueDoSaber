package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository repository;

    @Autowired
    public AlunoController(AlunoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro() {
        return "alunos";
    }

    @PostMapping("/cadastrar")
    public String cadastrarAluno(@RequestParam String nome,
                                 @RequestParam String email,
                                 @RequestParam String telefone) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
        repository.save(aluno);
        return "redirect:/alunos/lista";
    }

    @GetMapping("/lista")
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", repository.findAll());
        return "alunos-lista";
    }

    @PostMapping("/deletar/{id}")
    public String deletarAluno(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/alunos/lista";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Aluno aluno = repository.findById(id).orElseThrow();
        model.addAttribute("aluno", aluno);
        return "alunos-editar";
    }

    @PostMapping("/editar")
    public String editarAluno(@RequestParam Long id,
                              @RequestParam String nome,
                              @RequestParam String email,
                              @RequestParam String telefone) {
        Aluno aluno = repository.findById(id).orElseThrow();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
        repository.save(aluno);
        return "redirect:/alunos/lista";
    }

// Rotas REST para AJAX

    @PostMapping("/api/cadastrar")
    @ResponseBody
    public Aluno cadastrarViaAjax(@RequestParam String nome,
                                  @RequestParam String email,
                                  @RequestParam String telefone) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
        return repository.save(aluno);
    }

    @GetMapping("/api/listar")
    @ResponseBody
    public List<Aluno> listarJson() {
        return repository.findAll();
    }

    @GetMapping("/api/quantidade")
    @ResponseBody
    public long quantidade() {
        return repository.count();
    }

    @GetMapping("/api/editar")
    @ResponseBody
    public Aluno editarViaGet(@RequestParam Long id,
                              @RequestParam String nome,
                              @RequestParam String email,
                              @RequestParam String telefone) {
        Aluno aluno = repository.findById(id).orElseThrow();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
        return repository.save(aluno);
    }

    @DeleteMapping("/api/deletar/{id}")
    @ResponseBody
    public void deletarViaDelete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}