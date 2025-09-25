package com.example.biblioteca.repository;

import com.example.biblioteca.database.Conexao;
import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.model.Livro;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoDAO {
    public void insert (Emprestimo emprestimo) throws SQLException {
        String query = "INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES(?,?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){


            stmt.executeUpdate();

            System.out.println("Emprestimo inserido com sucesso!");
        }
    }

    public List<Emprestimo> buscarTodos() throws SQLException{
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM livro";

        List<Emprestimo> emprestimoList = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Emprestimo emprestimo = new Emprestimo();
                emprestimoList.add(emprestimo);
            }
        }
        return emprestimoList;
    }

    public Emprestimo buscarPorId(int id) throws SQLException{
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo WHERE id = ?";

        int newID = 0;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newID = rs.getInt("id");

            }
        }
        return new Emprestimo(newID, );
    }

    public void atualizar (Livro livro) throws SQLException {
        String query = "UPDATE emprestimo SET  WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){


            stmt.executeUpdate();

            System.out.println("Emprestimo atualizado com sucesso!");
        }
    }

    public void deletar (int id) throws SQLException {
        String query = "DELETE FROM emprestimo WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Emprestimo deletado com sucesso!");
        }
    }
}
