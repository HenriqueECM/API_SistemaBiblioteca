package com.example.biblioteca.controller;

import com.example.biblioteca.dto.livro.CriacaoLivroRequisicaoDto;
import com.example.biblioteca.dto.livro.CriacaoLivroRespostaDto;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity <CriacaoLivroRespostaDto> create (@RequestBody CriacaoLivroRequisicaoDto requisicaoLivro) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.create(requisicaoLivro));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity <List<CriacaoLivroRespostaDto>> get (){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarTodos());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <CriacaoLivroRespostaDto> getById(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarPorId(id));
        } catch (SQLException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <CriacaoLivroRespostaDto> put(@PathVariable int id, @RequestBody CriacaoLivroRequisicaoDto requisicaoLivro){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.update(id, requisicaoLivro));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}