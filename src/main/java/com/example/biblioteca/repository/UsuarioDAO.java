package com.example.biblioteca.repository;

import com.example.biblioteca.database.Conexao;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDAO {
    public void insert (Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario (nome, email) VALUES(?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());

            stmt.executeUpdate();

            System.out.println("Usuário inserido com sucesso!");
        }
    }

    public List<Usuario> buscarTodos() throws SQLException{
        String query = "SELECT id, nome, email FROM usuario";

        List<Usuario> usuarioList = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                Usuario usuario = new Usuario(id, nome, email);
                usuarioList.add(usuario);
            }
        }
        return usuarioList;
    }

    public Usuario buscarPorId(int id) throws SQLException{
        String query = "SELECT id, nome, email FROM usuario WHERE id = ?";

        int newID = 0;
        String nome = "";
        String email = "";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                newID = rs.getInt("id");
                nome = rs.getString("nome");
                email = rs.getString("email");
            }
        }
        return new Usuario(newID, nome, email);
    }

    public void atualizar (Usuario usuario) throws SQLException {
        String query = "UPDATE usuario SET nome = ?, email = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();

            System.out.println("Livro atualizado com sucesso!");
        }
    }

    public void deletar (int id) throws SQLException {
        String query = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Usuario deletado com sucesso!");
        }
    }
}
