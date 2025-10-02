package com.example.biblioteca.repository;

import com.example.biblioteca.database.Conexao;
import com.example.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo";

        List<Emprestimo> emprestimoList = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Date dataDevolucao = null;
                int id = rs.getInt("id");
                int idLivro = rs.getInt("livro_id");
                int idUsuario = rs.getInt("usuario_id");
                Date dataEmprestimo = rs.getDate("data_emprestimo");
                dataDevolucao = rs.getDate("data_devolucao");

                var emprestimo = new Emprestimo();

                if(dataDevolucao == null) {
                    emprestimo = new Emprestimo(id, idLivro, idUsuario, dataEmprestimo.toLocalDate(), null);
                }else{
                    emprestimo = new Emprestimo(id, idLivro, idUsuario, dataEmprestimo.toLocalDate(), dataDevolucao.toLocalDate());
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
                Date dataEmprestimo = rs.getDate("data_emprestimo");
                Date dataDevolucao = rs.getDate("data_devolucao");

                if (dataDevolucao == null) {
                    emprestimo = new Emprestimo(newId, idLivro, idUsuario,
                            dataEmprestimo.toLocalDate(), null);
                } else {
                    emprestimo = new Emprestimo(newId, idLivro, idUsuario,
                            dataEmprestimo.toLocalDate(), dataDevolucao.toLocalDate());
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

    // para mudar data prevista
    public Emprestimo atualizar (Emprestimo emprestimo) throws SQLException {
        String query = "UPDATE emprestimo SET data_emprestimo = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setInt(2, emprestimo.getId());
            stmt.executeUpdate();

            System.out.println("Emprestimo atualizado com sucesso!");
        }
        return emprestimo;
    }

    // para registrar a devolução
    public Emprestimo atualizarDevolucao (Emprestimo emprestimo) throws SQLException {
        String query = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setInt(2, emprestimo.getId());
            stmt.executeUpdate();

            System.out.println("Emprestimo atualizado com sucesso!");
        }
        return emprestimo;
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
