package com.jee.yougetnicecar.exceptions;

import com.jee.yougetnicecar.models.Utilisateur;

public class UpdateAccountException extends RuntimeException {

    private Utilisateur utilisateur;

    public UpdateAccountException(String message, Utilisateur utilisateur) {
        super(message);
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
