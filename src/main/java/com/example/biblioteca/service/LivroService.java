package com.example.biblioteca.service;

import com.example.biblioteca.dto.livro.CriacaoLivroRequisicaoDto;
import com.example.biblioteca.dto.livro.CriacaoLivroRespostaDto;
import com.example.biblioteca.mapper.LivroMapper;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.LivroDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {
    private final LivroDAO repository;
    private final LivroMapper mapper;

    public LivroService(LivroDAO repository, LivroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // criar livro
    public CriacaoLivroRespostaDto create (CriacaoLivroRequisicaoDto requisicaoDto) throws SQLException {
        return mapper.paraResposta(repository.insert(mapper.paraEntidade(requisicaoDto)));
    }

    // listar todos livros
    public List<CriacaoLivroRespostaDto> buscarTodos() throws SQLException {
        List<Livro> livros = repository.buscarTodos();

        List<CriacaoLivroRespostaDto> respostaDtos = new ArrayList<>();

        livros.forEach(livro -> {
            respostaDtos.add(mapper.paraResposta(livro));
        });

        return respostaDtos;
    }

    // buscar por id
    public CriacaoLivroRespostaDto buscarPorId(int id) throws SQLException {
        Livro livro = repository.buscarPorId(id);

        if (livro == null){
            throw new RuntimeException("Livro ID " + id + " não encontrado!");
        }
        return mapper.paraResposta(repository.buscarPorId(id));
    }

    // atualizar o livro
    public CriacaoLivroRespostaDto update (int id, CriacaoLivroRequisicaoDto requisicaoDto) throws SQLException {
        Livro livro = repository.buscarPorId(id);

        if (livro == null){
            throw new RuntimeException("Livro ID " + id + " não encontrado!");
        }

        return mapper.paraResposta(repository.atualizar(mapper.verificacaoUpdate(requisicaoDto, livro)));
    }

    // deletar livro
    public void delete (int id) throws SQLException {
        if (!repository.livroExiste(id)){
            throw new RuntimeException("Livro ID " + id + " não encontrado!");
        }
        repository.deletar(id);
    }
}