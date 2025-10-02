package com.example.biblioteca.controller;

import com.example.biblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.biblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.biblioteca.dto.livro.CriacaoLivroRespostaDto;
import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity <CriacaoEmprestimoRespostaDto> createEmprestimo (@RequestBody CriacaoEmprestimoRequisicaoDto requisicaoEmprestimo){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.create(requisicaoEmprestimo));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    // listar todos emprestimos - teste ok
    @GetMapping
    public ResponseEntity <List<CriacaoEmprestimoRespostaDto>> getEmprestimo(){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarTodos());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }

    // busca por id
    @GetMapping("/{id}")
    public ResponseEntity <CriacaoEmprestimoRespostaDto> getById(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarPorId(id));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    // vai alterar a data prevista
    @PutMapping("/{id}/emprestimo")
    // quando so vou atualizar data somente, colocar void, pois com return ele retorna o "objeto"
    public ResponseEntity <CriacaoEmprestimoRespostaDto> updateEmprestimo (@PathVariable int id, @RequestBody CriacaoEmprestimoRequisicaoDto requisicaoEmprestimo){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.updateEmprestimo(id,requisicaoEmprestimo));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    // vai alterar a data devolução
    @PutMapping("/{id}/devolucao")
    public ResponseEntity <CriacaoEmprestimoRespostaDto> updateEmprestimoDevolucao(@PathVariable int id, @RequestBody CriacaoEmprestimoRequisicaoDto requisicaoEmprestimo){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.updateDevolucao(id,requisicaoEmprestimo));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    // deletar o emprestimo - teste ok
    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteEmprestimo (@PathVariable int id){
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}