package com.jee.yougetnicecar.exceptions;

import com.jee.yougetnicecar.dtos.UtilisateurInscriptionDto;

public class InscriptionException extends RuntimeException{
    private final UtilisateurInscriptionDto utilisateurInscriptionDto;

    public InscriptionException(String msg, UtilisateurInscriptionDto utilisateurInscriptionDto) {
        super(msg);
        this.utilisateurInscriptionDto = utilisateurInscriptionDto;
    }

    public UtilisateurInscriptionDto getUtilisateurInscriptionDto() {
        return this.utilisateurInscriptionDto;
    }
}
