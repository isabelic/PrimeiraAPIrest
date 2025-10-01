package com.example.sistemaBiblioteca.dto.emprestimo;

import java.time.LocalDate;

public record CriacaoEmprestimoRespostaDto(
        int id,
         int idLivro,
         int idUsuario,
         LocalDate dataEmprestimo,
         LocalDate dataDevolucao
) {
}
