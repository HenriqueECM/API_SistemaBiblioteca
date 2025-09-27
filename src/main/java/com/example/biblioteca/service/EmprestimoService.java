package com.example.biblioteca.service;

import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.repository.EmprestimoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class EmprestimoService {
    private final EmprestimoDAO repository;

    public EmprestimoService(EmprestimoDAO repository) {
        this.repository = repository;
    }

    // cadastrar emprestimo
    public Emprestimo createEmprestimo(Emprestimo emprestimo) throws SQLException {
        return repository.insert(emprestimo);
    }

    // listar todos emprestimos
    public List<Emprestimo> listEmprestimo () throws SQLException {
        return repository.buscarTodos();
    }

    // buscar emprestimo de um usuário especifico
    public Emprestimo listById(int id) throws SQLException {
        List<Emprestimo> emprestimoList = repository.buscarTodos();

        for (Emprestimo e : emprestimoList){
            if (e.getId() == id){
                return repository.buscarPorId(id);
            }
        }
        throw new RuntimeException("O emprestimo de ID " + id +  " não existe.");
    }

    // finalizar o emprestimo
    public Emprestimo updateEmprestimoDevolucao (int id, Emprestimo emprestimo) throws SQLException {
        List<Emprestimo> emprestimoList = repository.buscarTodos();

        emprestimo.setId(id);
        for (Emprestimo e : emprestimoList){
            if (e.getId() == id){
                 repository.atualizarDevolucao(emprestimo);
                 return emprestimo;
            }
        }
        throw new RuntimeException("O emprestimo de ID " + id +  " não existe.");
    }

    // deletar o emprestimo
    public void deleteEmprestimo (int id) throws SQLException {
        List<Emprestimo> emprestimoList = repository.buscarTodos();

        for (Emprestimo e : emprestimoList){
            if (e.getId() == id){
                repository.deletar(id);
                return;
            }
        }
        throw new RuntimeException("O emprestimo de ID " + id +  " não existe.");
    }

    // atualizar data prevista
    public Emprestimo updateEmprestimo(int id, Emprestimo emprestimo) throws SQLException {
        List<Emprestimo> emprestimoList = repository.buscarTodos();

        emprestimo.setId(id);
        for (Emprestimo e : emprestimoList){
            if (e.getId() == id){
                repository.atualizar(emprestimo);
                return emprestimo;
            }
        }
        throw new RuntimeException("O emprestimo de ID " + id +  " não existe.");
    }
}