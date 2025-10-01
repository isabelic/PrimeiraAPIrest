package com.example.sistemaBiblioteca.service;

import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.sistemaBiblioteca.mapper.UsuarioMapper;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.repository.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioDAO repository, UsuarioMapper  mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriacaoUsuarioRespostaDto criarUsuario(CriacaoUsuarioRequisicaoDto requisicaoDto) throws SQLException{
        return mapper.paraRespostaDto(repository.inserirUsuario(mapper.paraEntidade(requisicaoDto)));
    }

    public List<Usuario> listarUsuarios() throws SQLException{
        return repository.buscarTodosUsuarios();
    }

    public Usuario listarUsuarioPorId(int id) throws SQLException{
        return repository.buscarUsuarioPorID(id);
    }

    public Usuario atualizarUsuario(int id, Usuario usuario) throws SQLException{
        List<Usuario> usuarios = repository.buscarTodosUsuarios();

        for(Usuario u : usuarios){
            if(u.getId() == id){
                usuario.setId(id);
                repository.atualizarUsuario(usuario);
                return usuario;
            }
        }
        throw new RuntimeException("ID do usuário não existe!");
    }

    public Usuario deletarUsuario(int id) throws SQLException{
        repository.deletarUsuario(id);
        return null;
    }
}
