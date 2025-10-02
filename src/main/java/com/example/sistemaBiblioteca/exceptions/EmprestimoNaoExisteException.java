package com.example.sistemaBiblioteca.exceptions;

public class EmprestimoNaoExisteException extends  RuntimeException {

    public EmprestimoNaoExisteException(){
        super("Empréstimo não existe");
    }


}
