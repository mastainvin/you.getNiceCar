package com.jee.yougetnicecar.exceptions;

public class NotConnectedException extends RuntimeException {

    public NotConnectedException() {
        super("Vous n'êtes pas utilisateur");
    }

}
