package com.example.biblioteca.repository;

import com.example.biblioteca.database.Conexao;
import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.model.Livro;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.EditorAwareTag;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class EmprestimoDAO {
    public Emprestimo insert (Emprestimo emprestimo) throws SQLException {
        String query = "INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo) VALUES(?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, emprestimo.getLivroId());
            stmt.setInt(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                emprestimo.setId(rs.getInt(1));
            }

            System.out.println("Emprestimo inserido com sucesso!");
        }
        return emprestimo;
    }

    public List<Emprestimo> buscarTodos() throws SQLException{
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo WHERE data_devolucao = null";

        List<Emprestimo> emprestimoList = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int idLivro = rs.getInt("livro_id");
                int idUsuario = rs.getInt("usuario_id");
                Date dataEmprestimo = rs.getDate("data_emprestimo");
                Date dataDevolucao = rs.getDate("data_devolucao");

                Emprestimo emprestimo = new Emprestimo(id, idLivro, idUsuario, dataEmprestimo.toLocalDate(), dataDevolucao.toLocalDate());
                emprestimoList.add(emprestimo);
            }
        }
        return emprestimoList;
    }

    public Emprestimo buscarPorId(int id) throws SQLException{
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo WHERE id = ?";

        int newId = 0;
        int idLivro = 0;
        int idUsuario = 0;
        Date dataEmprestimo = null;
        Date dataDevolucao = null;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newId = rs.getInt("id");
                idLivro = rs.getInt("livro_id");
                idUsuario = rs.getInt("usuario_id");
                dataEmprestimo = rs.getDate("data_emprestimo");
                dataDevolucao = rs.getDate("data_devolucao");

            }
        }
        return new Emprestimo(newId, idLivro, idUsuario, dataEmprestimo.toLocalDate(), dataDevolucao.toLocalDate());
    }

    // para mudar data prevista
    public void atualizar (int id, LocalDate dateEmprestimo) throws SQLException {
        String query = "UPDATE emprestimo SET data_emprestimo = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1, Date.valueOf(dateEmprestimo));
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("Emprestimo atualizado com sucesso!");
        }
    }

    // para registrar a devolução
    public void atualizarDevolucao (int id, LocalDate dataDevolucao) throws SQLException {
        String query = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1, Date.valueOf(dataDevolucao));
            stmt.setInt(2, id);
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
