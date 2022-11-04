package com.jee.yougetnicecar.exceptions;

public class NotAdminException extends RuntimeException{

    public NotAdminException() {
        super("Vous n'êtes pas administrateur");
    }

}
