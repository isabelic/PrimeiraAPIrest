package com.example.sistemaBiblioteca.service;

import com.example.sistemaBiblioteca.dto.livro.CriacaoLivroRequisicaoDto;
import com.example.sistemaBiblioteca.dto.livro.CriacaoLivroRespostaDto;
import com.example.sistemaBiblioteca.mapper.LivroMapper;
import com.example.sistemaBiblioteca.mapper.UsuarioMapper;
import com.example.sistemaBiblioteca.model.Livro;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.repository.LivroDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class LivroService {
    private final LivroDAO repository;
    private final LivroMapper mapper;

    public LivroService(LivroDAO repository, LivroMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;

    }

    public CriacaoLivroRespostaDto criarLivro(CriacaoLivroRequisicaoDto requisicaoDto) throws SQLException {
        return mapper.paraLivroDto(repository.inserirLivro(mapper.paraEntidade(requisicaoDto)));
    }

    public List<CriacaoLivroRespostaDto> listarLivros() throws SQLException{
        return repository.buscarTodosLivros().stream()
                .map(mapper:: paraLivroDto)
                .toList();
    }

    public CriacaoLivroRespostaDto listarLivroPorID(int id) throws SQLException{
        Livro livro = repository.buscarLivroPorID(id);

        if(livro.getId() == 0){
            throw new RuntimeException("id do livro não existe!");
        } else{
            return mapper.paraLivroDto(repository.buscarLivroPorID(id));
        }
    }

    public CriacaoLivroRespostaDto atualizarLivro(int id, Livro livro) throws SQLException{
        List<Livro> livros = repository.buscarTodosLivros();

        for(Livro li : livros){
            if(li.getId() == id){
                livro.setId(id);
                repository.atualizarLivro(livro);
                return mapper.paraLivroDto(livro);
            }
        }
        throw new RuntimeException("ID do usuário não existe!");
    }

    public void deletarLivro(int id) throws SQLException{
        Livro livro = repository.buscarLivroPorID(id);

        if(!repository.livroNaoExiste(id)){
            throw  new RuntimeException("Livro inexistente!");
        }

        repository.deletarLivro(id);

    }
}
