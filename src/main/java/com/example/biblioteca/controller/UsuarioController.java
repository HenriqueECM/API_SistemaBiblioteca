package com.example.biblioteca.controller;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario createUser (@RequestBody Usuario usuario){
        Usuario newUser = new Usuario();

        try {
            newUser = service.createUser(usuario);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newUser;
    }

    @GetMapping
    public List<Usuario> getUser(){
        List<Usuario> usuarioList = new ArrayList<>();

        try{
            usuarioList = service.listUser();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return usuarioList;
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable int id){
        Usuario newUser = new Usuario();

        try {
            newUser = service.listById(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newUser;
    }

    @PutMapping("/{id}")
    public Usuario updateUser(@PathVariable int id, @RequestBody Usuario usuario){
        Usuario newUser = new Usuario();

        try{
            newUser = service.updateUser(id, usuario);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newUser;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        try{
            service.deleteUser(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}