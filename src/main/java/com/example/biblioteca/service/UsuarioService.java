package com.example.biblioteca.service;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioDAO repository;

    public UsuarioService (UsuarioDAO repository){
        this.repository = repository;
    }

    // criar usuario
    public Usuario createUser(Usuario usuario) throws SQLException {
        return repository.insert(usuario);
    }

    // listar todos usuarios
    public List<Usuario> listUser () throws SQLException {
        return repository.buscarTodos();
    }

    // buscar por id
    public Usuario listById (int id) throws SQLException {
        // aplicar lista de todos usuarios para validação
        List<Usuario> usuarioList = repository.buscarTodos();
        for (Usuario u : usuarioList){
            // verificar se o id recebido é igual na lista para validar
            if (u.getId() == id){
                return repository.buscarPorId(id);
            }
        }
        // vai mostrar mensagem de erro da validação caso não existir
        throw new RuntimeException("O ID " + id + " do usuário não existe.");
    }

    // atualizar o usuario
    public Usuario updateUser(int id, Usuario usuario) throws SQLException {
        List<Usuario> usuarioList = repository.buscarTodos();

        usuario.setId(id);
        for (Usuario u : usuarioList){
            // verificar se o id recebido é igual na lista para validar
            if (u.getId() == id){
                repository.atualizar(usuario);
                return usuario;
            }
        }
        throw new RuntimeException("O ID " + id + " do usuário não existe.");
    }

    // deletar usuario
    public void deleteUser(int id) throws SQLException {
        List<Usuario> usuarioList = repository.buscarTodos();
        for(Usuario u : usuarioList){
            if (u.getId() == id){
                repository.deletar(id);
                return;
            }
        }
        throw new RuntimeException("O ID " + id + " do usuário não existe.");
    }
}