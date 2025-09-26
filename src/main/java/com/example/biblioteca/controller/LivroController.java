package com.example.biblioteca.controller;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.service.LivroService;
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
    public Livro createLivro (@RequestBody Livro livro) {
        Livro newLivro = new Livro();

        try {
            newLivro = service.createLivro(livro);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newLivro;
    }

    @GetMapping
    public List<Livro> getLivro (){
        List<Livro> livroList = new ArrayList<>();

        try {
            livroList = service.listLivro();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return livroList;
    }

    @GetMapping("/{id}")
    public Livro getById(@PathVariable int id){
        Livro newLivro = new Livro();

        try {
            newLivro = service.listById(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newLivro;
    }

    @PutMapping("/{id}")
    public Livro putLivro(@PathVariable int id, @RequestBody Livro livro){
        Livro newLivro = new Livro();

        try{
            newLivro = service.updateLivro(id, livro);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newLivro;
    }

    @DeleteMapping("/{id}")
    public void deleteLivro(@PathVariable int id){
        Livro newLivro = new Livro();

        try {
            service.deleteLivro(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}