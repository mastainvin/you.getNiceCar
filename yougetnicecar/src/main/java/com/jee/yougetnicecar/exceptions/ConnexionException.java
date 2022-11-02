package com.jee.yougetnicecar.exceptions;

import com.jee.yougetnicecar.dtos.UtilisateurConnexionDto;

public class ConnexionException extends RuntimeException{
    private final UtilisateurConnexionDto utilisateurConnexionDto;

    public ConnexionException(String msg, UtilisateurConnexionDto utilisateurConnexionDto) {
        super(msg);
        this.utilisateurConnexionDto = utilisateurConnexionDto;
    }

    public UtilisateurConnexionDto getUtilisateurConnexionDto() {
        return utilisateurConnexionDto;
    }
}
