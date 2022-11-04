package com.jee.yougetnicecar.models;

public enum Role {
    ADMIN,
    USER;

    public boolean getADMIN() {
        return this == ADMIN;
    }
}
