package com.example.sistemaBiblioteca.dto.livro;

public record CriacaoLivroRespostaDto(
         int id,
         String titulo,
         String autor,
       int  anoPublicacao
) {
}
