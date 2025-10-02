package com.example.biblioteca.repository;

import com.example.biblioteca.database.Conexao;
import com.example.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class EmprestimoDAO {
    public Emprestimo create (Emprestimo emprestimo) throws SQLException {
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
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo";

        List<Emprestimo> emprestimoList = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                LocalDate dataDevolucao = null;
                int id = rs.getInt("id");
                int idLivro = rs.getInt("livro_id");
                int idUsuario = rs.getInt("usuario_id");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                dataDevolucao = rs.getDate("data_devolucao").toLocalDate();

                Emprestimo emprestimo = new Emprestimo();

                if(dataDevolucao == null) {
                    emprestimo = new Emprestimo(id, idLivro, idUsuario, dataEmprestimo, null);
                }else{
                    emprestimo = new Emprestimo(id, idLivro, idUsuario, dataEmprestimo, dataDevolucao);
                }
                emprestimoList.add(emprestimo);
            }
        }
        return emprestimoList;
    }

    public List<Emprestimo> buscarEmprestimosPorUsuario(int usuario) throws SQLException{
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo WHERE usuario_id = ?";

        List<Emprestimo> emprestimoList = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, usuario);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                LocalDate dataDevolucao = null;
                int id = rs.getInt("id");
                int idLivro = rs.getInt("livro_id");
                int idUsuario = rs.getInt("usuario_id");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                dataDevolucao = rs.getDate("data_devolucao").toLocalDate();

                Emprestimo emprestimo = new Emprestimo();

                if(dataDevolucao == null) {
                    emprestimo = new Emprestimo(id, idLivro, idUsuario, dataEmprestimo, null);
                }else{
                    emprestimo = new Emprestimo(id, idLivro, idUsuario, dataEmprestimo, dataDevolucao);
                }
                emprestimoList.add(emprestimo);
            }
        }
        return emprestimoList;
    }

    public Emprestimo buscarPorId(int id) throws SQLException {
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo WHERE id = ?";

        Emprestimo emprestimo = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int newId = rs.getInt("id");
                int idLivro = rs.getInt("livro_id");
                int idUsuario = rs.getInt("usuario_id");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("data_devolucao").toLocalDate();

                if (dataDevolucao == null) {
                    emprestimo = new Emprestimo(newId, idLivro, idUsuario,
                            dataEmprestimo, null);
                } else {
                    emprestimo = new Emprestimo(newId, idLivro, idUsuario,
                            dataEmprestimo, dataDevolucao);
                }
            }
        }

        return emprestimo;
    }

    public boolean existeEmprestimo (int id) throws SQLException {
        String query = "SELECT id FROM emprestimo WHERE id = ?";

        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }
        }
        return false;
    }

    public boolean livroJaEmprestado (int id) throws SQLException {
        String query = "SELECT id FROM emprestimo WHERE livro_id = ? and data_devolucao IS NULL";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return true;
            }
        }
        return false;
    }

    // para mudar data prevista
    public void update (int id, LocalDate dataEmprestimo) throws SQLException {
        String query = "UPDATE emprestimo SET data_emprestimo = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1, Date.valueOf(dataEmprestimo));
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("Emprestimo atualizado com sucesso!");
        }
    }

    // para registrar a devolução
    public void updateDevolucao (int id, LocalDate dataDevolucao) throws SQLException {
        String query = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1, Date.valueOf(dataDevolucao));
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("Emprestimo atualizado com sucesso!");
        }
    }

    public void delete (int id) throws SQLException {
        String query = "DELETE FROM emprestimo WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Emprestimo deletado com sucesso!");
        }
    }
}
