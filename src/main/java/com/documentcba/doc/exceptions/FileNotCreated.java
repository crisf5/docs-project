package com.documentcba.doc.exceptions;

public class FileNotCreated extends RuntimeException{
    public FileNotCreated(){
        super("Se produjo un error (No se subieron archivos o se pasó mal el parametro)");
    }
}
