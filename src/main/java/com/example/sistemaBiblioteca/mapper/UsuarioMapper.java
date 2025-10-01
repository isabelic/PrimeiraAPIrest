package com.example.sistemaBiblioteca.mapper;

import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.sistemaBiblioteca.model.Usuario;

public class UsuarioMapper {


    public Usuario paraEntidade(CriacaoUsuarioRequisicaoDto requisicaoDto){
        return new Usuario(requisicaoDto.nome(),requisicaoDto.email());
    }

    public CriacaoUsuarioRespostaDto paraRespostaDto(Usuario usuario) {
        return new CriacaoUsuarioRespostaDto(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
