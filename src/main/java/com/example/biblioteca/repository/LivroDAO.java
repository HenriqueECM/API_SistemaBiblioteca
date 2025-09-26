package com.example.biblioteca.repository;

import com.example.biblioteca.database.Conexao;
import com.example.biblioteca.model.Livro;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroDAO {

    public Livro insert (Livro livro) throws SQLException {
        String query = "INSERT INTO livro (titulo, autor, ano_publicacao) VALUES(?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.executeUpdate();

            System.out.println("Livro inserido com sucesso!");
        }
        return livro;
    }

    public List<Livro> buscarTodos() throws SQLException{
        String query = "SELECT id, titulo, autor, ano_publicacao FROM livro";

        List<Livro> livroList = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoPublicacao = rs.getInt("ano_publicacao");
                Livro livro = new Livro(id, titulo, autor, anoPublicacao);
                livroList.add(livro);
            }
        }
        return livroList;
    }

    public Livro buscarPorId(int id) throws SQLException{
        String query = "SELECT id, titulo, autor, ano_publicacao FROM livro WHERE id = ?";

        int newID = 0;
        String titulo = "";
        String autor = "";
        int anoPublicacao = 0;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newID = rs.getInt("id");
                titulo = rs.getString("titulo");
                autor = rs.getString("autor");
                anoPublicacao = rs.getInt("ano_publicacao");
            }
        }
        return new Livro(newID, titulo, autor, anoPublicacao);
    }

    public void atualizar (Livro livro) throws SQLException {
        String query = "UPDATE livro SET titulo = ?, autor = ?, ano_publicacao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getId());

            stmt.executeUpdate();

            System.out.println("Livro atualizado com sucesso!");
        }
    }

    public void deletar (int id) throws SQLException {
        String query = "DELETE FROM livro WHERE id = ?";

        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Livro deletado com sucesso!");
        }
    }
}