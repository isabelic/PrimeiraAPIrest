package com.example.sistemaBiblioteca.service;

import com.example.sistemaBiblioteca.model.Livro;
import com.example.sistemaBiblioteca.repository.LivroDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class LivroService {
    private final LivroDAO repository;

    public LivroService(LivroDAO repository) {
        this.repository = repository;
    }

    public Livro criarLivro(Livro livro) throws SQLException {
        return repository.inserirLivro(livro);
    }

    public List<Livro> listarLivros() throws SQLException{
        return repository.buscarTodosLivros();
    }

    public Livro listarLivroPorID(int id) throws SQLException{
        return repository.buscarLivroPorID(id);
    }

    public Livro atualizarLivro(int id, Livro livro) throws SQLException{
        List<Livro> livros = repository.buscarTodosLivros();

        for(Livro li : livros){
            if(li.getId() == id){
                livro.setId(id);
                repository.atualizarLivro(livro);
                return livro;
            }
        }
        throw new RuntimeException("ID do usuário não existe!");
    }

    public Livro deletarLivro(int id) throws SQLException{
        repository.deletarLivro(id);
        return null;
    }
}
