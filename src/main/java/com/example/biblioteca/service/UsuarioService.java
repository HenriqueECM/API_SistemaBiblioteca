package com.example.biblioteca.service;

import com.example.biblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.biblioteca.dto.usuario.CriacaorUsuarioRequisicaoDto;
import com.example.biblioteca.mapper.UsuarioMapper;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioDAO repository;
    private final UsuarioMapper mapper;

    public UsuarioService (UsuarioDAO repository, UsuarioMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    // criar usuario
    public CriacaoUsuarioRespostaDto createUser(CriacaorUsuarioRequisicaoDto requisicaoDto) throws SQLException {
        return mapper.paraResposta(repository.insert(mapper.paraEntidade(requisicaoDto)));
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