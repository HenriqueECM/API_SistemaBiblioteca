package com.example.biblioteca.controller;

import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    // criar o emprestimo - teste ok
    @PostMapping
    public Emprestimo createEmprestimo (@RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.createEmprestimo(emprestimo);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newEmprestimo;
    }

    // listar todos emprestimos
    @GetMapping
    public List<Emprestimo> getEmprestimo(){
        List<Emprestimo> emprestimoList = new ArrayList<>();

        try{
            emprestimoList = service.listEmprestimo();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return emprestimoList;
    }

    // busca por id
    @GetMapping("/{id}")
    public Emprestimo getById(@PathVariable int id){
        Emprestimo newEmprestimo = new Emprestimo();

        try {
            newEmprestimo = service.listById(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newEmprestimo;
    }

    // vai alterar a data prevista
    @PutMapping("/{id}/emprestimo")
    // quando so vou atualizar data somente, colocar void, pois com return ele retorna o "objeto"
    public void updateEmprestimo (@PathVariable int id, @RequestBody LocalDate emprestimo){

        try {
            service.updateEmprestimo(id, emprestimo);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // deletar o emprestimo
    @DeleteMapping("/{id}")
    public void deleteEmprestimo (@PathVariable int id){

        try {
            service.deleteEmprestimo(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // vai alterar a data devolução
    @PutMapping("/{id}/devolucao")
    public void updateEmprestimoDevolucao(@PathVariable int id, @RequestBody LocalDate devolucao){

        try {
            service.updateEmprestimoDevolucao(id, devolucao);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}