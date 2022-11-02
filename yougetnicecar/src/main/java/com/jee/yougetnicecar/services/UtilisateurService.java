package com.jee.yougetnicecar.services;

import com.jee.yougetnicecar.dtos.UtilisateurInscriptionDto;
import com.jee.yougetnicecar.models.Panier;
import com.jee.yougetnicecar.models.Role;
import com.jee.yougetnicecar.models.Utilisateur;
import com.jee.yougetnicecar.repositories.PanierRepository;
import com.jee.yougetnicecar.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PanierRepository panierRepository;

    public Utilisateur creerUtilisateur(UtilisateurInscriptionDto utilisateurInscriptionDto) {
        Panier panierCourant = new Panier();
        panierRepository.save(panierCourant);

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setPanier_courant(panierCourant);
        utilisateur.setNom(utilisateurInscriptionDto.getNom());
        utilisateur.setLogin(utilisateurInscriptionDto.getUsername());
        utilisateur.setPassword(utilisateurInscriptionDto.getPassword());
        utilisateur.setPrenom(utilisateurInscriptionDto.getPrenom());
        utilisateur.setRole(Role.USER);
        utilisateurRepository.save(utilisateur);

        return utilisateur;
    }

}
