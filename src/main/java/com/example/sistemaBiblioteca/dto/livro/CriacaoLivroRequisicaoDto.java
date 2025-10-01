package com.example.sistemaBiblioteca.dto.livro;

public record CriacaoLivroRequisicaoDto(
        String titulo,
        String autor,
        int  anoPublicacao
) {
}
