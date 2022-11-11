package com.jee.yougetnicecar.services;

import com.jee.yougetnicecar.dtos.CommandeDto;
import com.jee.yougetnicecar.dtos.UtilisateurInscriptionDto;
import com.jee.yougetnicecar.models.*;
import com.jee.yougetnicecar.repositories.PanierRepository;
import com.jee.yougetnicecar.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

        utilisateur.setPanierCourant(panierCourant);
        utilisateur.setNom(utilisateurInscriptionDto.getNom());
        utilisateur.setLogin(utilisateurInscriptionDto.getUsername());
        utilisateur.setPassword(utilisateurInscriptionDto.getPassword());
        utilisateur.setPrenom(utilisateurInscriptionDto.getPrenom());
        utilisateur.setRole(Role.USER);
        utilisateurRepository.save(utilisateur);

        return utilisateur;
    }

    public boolean loginExiste(String login) {
        return utilisateurRepository.findByLogin(login).isPresent();
    }

    public List<CommandeDto> getCommandes(Utilisateur utilisateur) {
        List<Panier> anciensPaniers = panierRepository.findByUtilisateurAndEtatPanierOrderByDateDesc(utilisateur, EtatPanier.PAYE);
        List<CommandeDto> commandeDtos = new ArrayList<>();

        for(Panier panier : anciensPaniers){
            CommandeDto commandeDto = new CommandeDto();
            commandeDto.setPanierId(panier.getId());
            HashMap<Produit, Integer> produitQuantite = new HashMap<>();
            Integer total = 0;
            for (Produit produit : panier.getProduits()) {
                total += produit.getPrix();
                boolean found = false;
                for (Produit produit1 : produitQuantite.keySet()) {
                    if (Objects.equals(produit.getId(), produit1.getId())) {
                        produitQuantite.put(produit1, produitQuantite.get(produit1) + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    produitQuantite.put(produit, 1);
                }
            }

            commandeDto.setTotal(total);
            commandeDto.setProduits(produitQuantite);
            commandeDto.setDate(panier.getDate());
            commandeDtos.add(commandeDto);
        }
        return commandeDtos;
    }

}
