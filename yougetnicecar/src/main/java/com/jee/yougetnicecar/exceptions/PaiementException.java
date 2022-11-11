package com.jee.yougetnicecar.exceptions;

import com.jee.yougetnicecar.dtos.CarteBleueDto;
import com.jee.yougetnicecar.models.Utilisateur;

public class PaiementException extends RuntimeException {
    private final Utilisateur utilisateur;
    private final Integer montant;
    private final CarteBleueDto carteBleueDto;


    public PaiementException(String message, Utilisateur utilisateur, Integer montant, CarteBleueDto carteBleueDto) {
        super(message);
        this.utilisateur = utilisateur;
        this.montant = montant;
        this.carteBleueDto = carteBleueDto;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Integer getMontant() {
        return montant;
    }

    public CarteBleueDto getCarteBleueDto() {
        return carteBleueDto;
    }
}
