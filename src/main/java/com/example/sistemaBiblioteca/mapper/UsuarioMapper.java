package com.example.sistemaBiblioteca.mapper;

import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.sistemaBiblioteca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {


    public Usuario paraEntidade(CriacaoUsuarioRequisicaoDto requisicaoDto){
        return new Usuario(requisicaoDto.nome(),requisicaoDto.email());
    }

    public Usuario paraUpdate(CriacaoUsuarioRequisicaoDto requisicaoDto, Usuario usuario){
        if(requisicaoDto.nome() != usuario.getNome() && requisicaoDto.nome() != null){
            usuario.setNome(requisicaoDto.nome());
        }

        if(requisicaoDto.email() != requisicaoDto.email() && requisicaoDto.nome() != null){
            usuario.setEmail(requisicaoDto.email());
        }
        return usuario;
    }

    public CriacaoUsuarioRespostaDto paraRespostaDto(Usuario usuario) {
        return new CriacaoUsuarioRespostaDto(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
