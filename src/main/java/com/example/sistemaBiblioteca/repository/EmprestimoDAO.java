package com.example.sistemaBiblioteca.repository;

import com.example.sistemaBiblioteca.conexao.Conexao;
import com.example.sistemaBiblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoDAO {

    public Emprestimo inserirEmprestimo(Emprestimo emprestimo) throws SQLException{
        String query = "INSERT INTO emprestimo(livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES (?,?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1,emprestimo.getIdLivro());
            stmt.setInt(2,emprestimo.getIdUsuario());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4,Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                emprestimo.setId(rs.getInt(1));
            }
        }
        return emprestimo;
    }

    public List<Emprestimo> buscarTodosEmprestimos() throws SQLException{
        List<Emprestimo> emprestimos = new ArrayList<>();
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int idLivro = rs.getInt("idLivro");
                int idUsuario = rs.getInt("idUsuario");
                Date dataEmprestimo = rs.getDate("data_emprestimo");
                Date dataDevolucao = rs.getDate("data_devolucao");

                var emprestimo = new Emprestimo(id, idLivro, idUsuario, dataEmprestimo.toLocalDate(), dataDevolucao.toLocalDate());
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }

    public Emprestimo buscarEmprestimoPorID(int id) throws SQLException{
        String query = "SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo WHERE id = ?";

        int newID = 0;
        int idLivro = 0;
        int idUsuario = 0;
        Date dataEmprestimo = null;
        Date dataDevolucao = null;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                newID = rs.getInt("id");
                idLivro = rs.getInt("livro_id");
                idUsuario = rs.getInt("usuario_id");
                dataEmprestimo = rs.getDate("data_emprestimo");
                dataDevolucao = rs.getDate("data_devolucao");
            }
        }
        return new Emprestimo(newID, idLivro, idUsuario, dataEmprestimo.toLocalDate(), dataDevolucao.toLocalDate());
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) throws SQLException{
        String query = "UPDATE emprestimo SET data_emprestimo = ?, data_devolucao = ? WHERE id =?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1,Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(2,Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setInt(3,emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarEmprestimo(int id) throws SQLException{
        String query = "DELETE FROM emprestimo WHERE id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }
}
