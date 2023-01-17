package com.documentcba.doc.exceptions;

public class DocumentNotFound extends RuntimeException{

    public DocumentNotFound(){
        super("No hay ningún documento con ese nombre");
    }
}
