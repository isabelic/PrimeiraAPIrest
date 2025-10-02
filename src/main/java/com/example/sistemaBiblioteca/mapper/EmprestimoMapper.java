package com.example.sistemaBiblioteca.mapper;

import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.sistemaBiblioteca.model.Emprestimo;

public class EmprestimoMapper {



    public Emprestimo paraEntidade(CriacaoEmprestimoRequisicaoDto requisicaoDto){
         return new Emprestimo(requisicaoDto.idLivro(),requisicaoDto.idUsuario(),requisicaoDto.dataEmprestimo(),requisicaoDto.dataDevolucao());
    }

    public CriacaoEmprestimoRespostaDto paraEmprestimoDto(Emprestimo emprestimo){
        return new CriacaoUsuarioRespostaDto(emprestimo.getId(),emprestimo.getIdUsuario(),emprestimo.getIdLivro(),emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }

}
