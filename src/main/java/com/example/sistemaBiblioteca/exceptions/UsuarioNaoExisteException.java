package com.example.sistemaBiblioteca.exceptions;

public class UsuarioNaoExisteException  extends  RuntimeException {

    public UsuarioNaoExisteException (){
        super("usuario não existe");
    }



}
