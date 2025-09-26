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
        String query = "INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES(?,?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, emprestimo.getLivroId());
            stmt.setInt(2, emprestimo.getUsuarioId());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.executeUpdate();

            System.out.println("Emprestimo inserido com sucesso!");
        }
        return emprestimo;
    }

    public List<Emprestimo> buscarTodos() throws SQLException{
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo";

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

    public Emprestimo buscarPorId(int idUsuario) throws SQLException{
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo WHERE usuario_id = ?";

        int id = 0;
        int idLivro = 0;
        int newIdUsuario = 0;
        Date dataEmprestimo = null;
        Date dataDevolucao = null;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                id = rs.getInt("id");
                idLivro = rs.getInt("livro_id");
                newIdUsuario = rs.getInt("usuario_id");
                dataEmprestimo = rs.getDate("data_emprestimo");
                dataDevolucao = rs.getDate("data_devolucao");

            }
        }
        return new Emprestimo(id, idLivro, newIdUsuario, dataEmprestimo.toLocalDate(), dataDevolucao.toLocalDate());
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
