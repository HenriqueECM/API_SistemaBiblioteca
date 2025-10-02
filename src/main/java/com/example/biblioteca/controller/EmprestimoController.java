package com.example.biblioteca.controller;

import com.example.biblioteca.dto.dataDevDto.DataDevRequisicaoDto;
import com.example.biblioteca.dto.dataDevDto.DataEmpRequisicaoDto;
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
    public ResponseEntity <CriacaoEmprestimoRespostaDto> create (@RequestBody CriacaoEmprestimoRequisicaoDto requisicaoEmprestimo){
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
    public ResponseEntity <List<CriacaoEmprestimoRespostaDto>> buscarTodos(){
        try {
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
    public ResponseEntity <CriacaoEmprestimoRespostaDto> buscarPorId(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarPorId(id));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/usuarios/{id}/emprestimos")
    public ResponseEntity <List<CriacaoEmprestimoRespostaDto>> buscarEmprestimosPorUsuario(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarEmprestimosPorUsuario(id));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    // vai alterar a data prevista
    @PutMapping("/{id}/emprestimo")
    public ResponseEntity <Void> updateEmprestimo (@PathVariable int id, @RequestBody DataEmpRequisicaoDto dataEmpRequisicaoDto){
        try {
            service.updateEmprestimo(id, dataEmpRequisicaoDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    // vai alterar a data devolução
    @PutMapping("/{id}/devolucao")
    public ResponseEntity <Void> updateDevolucao(@PathVariable int id, @RequestBody DataDevRequisicaoDto dataDevRequisicaoDto){
        try {
            service.updateDevolucao(id, dataDevRequisicaoDto);
            return ResponseEntity.status(HttpStatus.OK)
                    // coloco build pois não estou retornando um valor, ou seja utilizando Void
                    .build();
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    // deletar o emprestimo - teste ok
    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete (@PathVariable int id){
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