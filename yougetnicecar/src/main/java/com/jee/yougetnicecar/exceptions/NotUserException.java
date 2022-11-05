package com.jee.yougetnicecar.exceptions;

public class NotUserException extends RuntimeException{

    public NotUserException() {
        super("Vous n'êtes pas utilisateur");
    }

}
