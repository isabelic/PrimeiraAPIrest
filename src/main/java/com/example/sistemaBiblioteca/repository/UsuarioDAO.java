package com.example.sistemaBiblioteca.repository;

import com.example.sistemaBiblioteca.conexao.Conexao;
import com.example.sistemaBiblioteca.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDAO {

    public Usuario inserirUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario(nome, email) VALUES (?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                usuario.setId(rs.getInt(1));
            }
        }
        return usuario;
    }

public List<Usuario> buscarTodosUsuarios() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT id, nome, email FROM usuario";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                var usuario = new Usuario(id, nome, email);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario buscarUsuarioPorID(int id) throws SQLException{
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

    public void atualizarUsuario(Usuario usuario) throws SQLException{
        String query = "UPDATE usuario SET nome = ?, email = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3,usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarUsuario (int id) throws SQLException{
        String query = "DELETE FROM usuario WHERE id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }
}
