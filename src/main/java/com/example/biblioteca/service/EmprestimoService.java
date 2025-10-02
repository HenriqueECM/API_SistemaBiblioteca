package com.example.biblioteca.service;

import com.example.biblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.biblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.biblioteca.mapper.EmprestimoMapper;
import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.repository.EmprestimoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {
    private final EmprestimoDAO repository;
    private final EmprestimoMapper mapper;

    public EmprestimoService(EmprestimoDAO repository, EmprestimoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // cadastrar emprestimo
    public CriacaoEmprestimoRespostaDto create(CriacaoEmprestimoRequisicaoDto requisicaoDto) throws SQLException {
        return mapper.paraResposta(repository.insert(mapper.paraEntidade(requisicaoDto)));
    }

    // listar todos emprestimos
    public List<CriacaoEmprestimoRespostaDto> buscarTodos () throws SQLException {
        List<CriacaoEmprestimoRespostaDto> respostaDtos = new ArrayList<>();
        List<Emprestimo> emprestimos = repository.buscarTodos();

        emprestimos.forEach(emprestimo -> {
            respostaDtos.add(mapper.paraResposta(emprestimo));
        });
        return respostaDtos;
    }

    // buscar emprestimo de um usuário especifico
    public CriacaoEmprestimoRespostaDto buscarPorId(int id) throws SQLException {
        Emprestimo emprestimo = repository.buscarPorId(id);

        if (emprestimo == null){
            throw new RuntimeException("O emprestimo de ID " + id +  " não existe.");
        }
        return mapper.paraResposta(repository.buscarPorId(id));
    }

    // finalizar o emprestimo // revisar o update do DTO do emprestimo
    public CriacaoEmprestimoRespostaDto updateDevolucao (int id, CriacaoEmprestimoRequisicaoDto requisicaoDto) throws SQLException {
        Emprestimo emprestimo = repository.buscarPorId(id);

        if (emprestimo == null){
            throw new RuntimeException("O emprestimo de ID " + id +  " não existe.");
        }

        return mapper.paraResposta(repository.atualizarDevolucao(mapper.verificarEmprestimo(requisicaoDto, emprestimo)));
    }

    // deletar o emprestimo
    public void delete (int id) throws SQLException {
        if (!repository.existeEmprestimo(id)){
            throw new RuntimeException("O emprestimo de ID " + id +  " não existe.");
        }

        repository.deletar(id);
    }

    // atualizar data prevista
    public CriacaoEmprestimoRespostaDto updateEmprestimo(int id, CriacaoEmprestimoRequisicaoDto requisicaoDto) throws SQLException {
        Emprestimo emprestimo = repository.buscarPorId(id);

        if (emprestimo == null){
            throw new RuntimeException("O emprestimo de ID " + id +  " não existe.");
        }

        return mapper.paraResposta(repository.atualizar(mapper.verificarEmprestimo(requisicaoDto, emprestimo)));
    }
}