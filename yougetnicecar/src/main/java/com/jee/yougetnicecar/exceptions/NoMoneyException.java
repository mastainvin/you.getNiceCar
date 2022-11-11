package com.jee.yougetnicecar.exceptions;

public class NoMoneyException extends RuntimeException {
    public NoMoneyException(String message) {
        super(message);
    }
}
