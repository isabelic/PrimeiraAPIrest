package com.example.sistemaBiblioteca.service;

import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.sistemaBiblioteca.mapper.EmprestimoMapper;
import com.example.sistemaBiblioteca.model.Emprestimo;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.repository.EmprestimoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoDAO repository;
    private final EmprestimoMapper mapper;

    public EmprestimoService(EmprestimoDAO repository, EmprestimoMapper mapper) {
        this.mapper = mapper;

        this.repository = repository;
    }

    public CriacaoEmprestimoRespostaDto criarEmprestimo(CriacaoEmprestimoRequisicaoDto requisicaoDto) throws SQLException {
        return mapper.paraEmprestimoDto(repository.inserirEmprestimo(mapper.paraEntidade(requisicaoDto)));
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
