package com.example.sistemaBiblioteca.exceptions;

public class LivroNaoExisteException  extends  RuntimeException{

    public LivroNaoExisteException(){

        super("Livro não existe");
    }
}
