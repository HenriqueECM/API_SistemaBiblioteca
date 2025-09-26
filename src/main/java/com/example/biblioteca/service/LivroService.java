package com.example.biblioteca.service;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repository.LivroDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {
    private final LivroDAO repository;

    public LivroService(LivroDAO repository) {
        this.repository = repository;
    }

    // criar livro
    public Livro createLivro (Livro livro) throws SQLException {
        return repository.insert(livro);
    }

    // listar todos livros
    public List<Livro> listLivro() throws SQLException {
        return repository.buscarTodos();
    }

    // buscar por id
    public Livro listById(int id) throws SQLException {
        List<Livro> livroList = repository.buscarTodos();

        for (Livro livro : livroList){
            if (livro.getId() == id){
                return repository.buscarPorId(id);
            }
        }
        throw new RuntimeException("O ID " + id + " do livro não existe.");
    }

    // atualizar o livro
    public Livro updateLivro (int id, Livro livro) throws SQLException {
        List<Livro> livroList = repository.buscarTodos();

        livro.setId(id);
        for (Livro l : livroList){
            if (l.getId() == id){
                repository.atualizar(livro);
                return livro;
            }
        }
        throw new RuntimeException("O ID " + id + " do livro não existe.");
    }

    // deletar livro
    public void deleteLivro (int id) throws SQLException {
        List<Livro> livroList = repository.buscarTodos();
        for (Livro livro : livroList){
            if (livro.getId() == id){
                repository.deletar(id);
                return;
            }
        }
        throw new RuntimeException("O ID " + id + " do livro não existe.");
    }
}