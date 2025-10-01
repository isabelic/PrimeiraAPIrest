package com.example.sistemaBiblioteca.service;

import com.example.sistemaBiblioteca.model.Emprestimo;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.repository.EmprestimoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoDAO repository;

    public EmprestimoService(EmprestimoDAO repository) {
        this.repository = repository;
    }

    public Emprestimo criarEmprestimo(Emprestimo emprestimo) throws SQLException {
        return repository.inserirEmprestimo(emprestimo);
    }

    public List<Emprestimo> listarEmprestimo() throws SQLException{
        return repository.buscarTodosEmprestimos();
    }

    public Emprestimo listarEmprestimoPorId(int id) throws SQLException{
        return repository.buscarEmprestimoPorID(id);
    }

    public Emprestimo atualizarEmprestimo(int id, Emprestimo emprestimo) throws SQLException{
        List<Emprestimo> emprestimos = repository.buscarTodosEmprestimos();

        for(Emprestimo emp : emprestimos){
            if(emp.getId() == id){
                emprestimo.setId(id);
                repository.atualizarEmprestimo(emprestimo);
                return emprestimo;
            }
        }
        throw new RuntimeException("ID do usuário não existe!");
    }

    public Emprestimo deletarEmprestimo(int id) throws SQLException{
        repository.deletarEmprestimo(id);
        return null;
    }
}
