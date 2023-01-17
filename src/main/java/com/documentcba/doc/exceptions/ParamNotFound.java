package com.documentcba.doc.exceptions;

public class ParamNotFound extends  RuntimeException{
    public ParamNotFound(String nameParam){
        super("El parametro "+ nameParam +" ingresado es incorrecto");
    }
}
