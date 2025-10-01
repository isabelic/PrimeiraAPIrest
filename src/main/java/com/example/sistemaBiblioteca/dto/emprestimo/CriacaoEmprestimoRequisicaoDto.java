package com.example.sistemaBiblioteca.dto.emprestimo;

import java.time.LocalDate;

public record CriacaoEmprestimoRequisicaoDto(
        int idLivro,
        int idUsuario,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
