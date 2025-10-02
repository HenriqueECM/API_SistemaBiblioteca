package com.example.biblioteca.service;

import com.example.biblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.biblioteca.dto.usuario.CriacaorUsuarioRequisicaoDto;
import com.example.biblioteca.mapper.UsuarioMapper;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public CriacaoUsuarioRespostaDto create(CriacaorUsuarioRequisicaoDto requisicaoDto) throws SQLException {
        return mapper.paraResposta(repository.insert(mapper.paraEntidade(requisicaoDto)));

        // codigo antigo
//        public Usuario createUser(Usuario usuario) throws SQLException {
//            return repository.insert(usuario);
//        }
    }

    // listar todos usuarios
    public List<CriacaoUsuarioRespostaDto> buscarTodos () throws SQLException {
        // codigo antigo
//        public List<Usuario> listUser () throws SQLException {
//            return repository.buscarTodos();
//        }

        List<Usuario> usuarios = repository.buscarTodos();

        List<CriacaoUsuarioRespostaDto> respostaDtos = new ArrayList<>();

        // OPÇÃO 1
        for (Usuario usuario : usuarios){
            respostaDtos.add(mapper.paraResposta(usuario));
        }

        return respostaDtos;

        // OPÇÃO 2
//        usuarios.forEach(usuario -> {
//            respostaDtos.add(mapper.paraResposta(usuario));
//        });
//
//        return respostaDtos;

        // OPÇÃO 3
//        return repository.buscarTodos().stream()
//                .map(mapper::paraResposta)
//                .toList();
    }

    // buscar por id
    public CriacaoUsuarioRespostaDto buscarPorId (int id) throws SQLException {

        // aplicação do DTO
        Usuario usuario = repository.buscarPorId(id);

        if(usuario == null){
            throw new RuntimeException("Usuario ID " + id + " não encontrado!");
        }

        return mapper.paraResposta(usuario);

        // aplicar lista de todos usuarios para validação
//        List<Usuario> usuarioList = repository.buscarTodos();
//        for (Usuario u : usuarioList){
            // verificar se o id recebido é igual na lista para validar
//            if (u.getId() == id){
//                return mapper.paraResposta(repository.buscarPorId(id));
//            }
//        }
        // vai mostrar mensagem de erro da validação caso não existir
//        throw new RuntimeException("O ID " + id + " do usuário não existe.");
    }

    // atualizar o usuario
    public CriacaoUsuarioRespostaDto update(int id, CriacaorUsuarioRequisicaoDto requisicaoDto) throws SQLException {
        Usuario usuario = repository.buscarPorId(id);

        if (usuario == null){
            throw new RuntimeException("Usuario ID " + id + " não encontrado!");
        }

        return mapper.paraResposta(repository.atualizar(mapper.verificacaoUpdate(requisicaoDto, usuario)));
    }

    // deletar usuario
    public void delete(int id) throws SQLException {
        if(!repository.usuarioExiste(id)){
            throw new RuntimeException("Usuario ID " + id + " não encontrado!");
        }
        repository.deletar(id);
    }

        // codigo antigo
//        List<Usuario> usuarioList = repository.buscarTodos();
//        for(Usuario u : usuarioList){
//            if (u.getId() == id){
//                repository.deletar(id);
//                return;
//            }
//        }
//        throw new RuntimeException("O ID " + id + " do usuário não existe.");
}