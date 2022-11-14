package com.jee.yougetnicecar.services;

import com.jee.yougetnicecar.dtos.CommandeDto;
import com.jee.yougetnicecar.dtos.UtilisateurConnexionDto;
import com.jee.yougetnicecar.dtos.UtilisateurInscriptionDto;
import com.jee.yougetnicecar.exceptions.ConnexionException;
import com.jee.yougetnicecar.exceptions.ResourceNotFoundException;
import com.jee.yougetnicecar.exceptions.UpdateAccountException;
import com.jee.yougetnicecar.models.*;
import com.jee.yougetnicecar.repositories.PanierRepository;
import com.jee.yougetnicecar.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PanierRepository panierRepository;

    protected Utilisateur verifyUtilisateur(Long userID) throws ResourceNotFoundException {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(userID);
        if (utilisateur.isEmpty()) {
            throw new ResourceNotFoundException("Utilisateur with id " + userID + " not found.");
        }
        return utilisateur.get();
    }

    public Utilisateur connexion(UtilisateurConnexionDto utilisateurConnexionDto) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByLoginAndPassword(utilisateurConnexionDto.getUsername(), utilisateurConnexionDto.getPassword());

        if (utilisateurOptional.isPresent()) {
            return utilisateurOptional.get();
        } else {
            throw new ConnexionException("Utilisateur ou mot de passe incorrect", utilisateurConnexionDto);
        }

    }

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

    public void modifierCompte(Utilisateur utilisateur, String nom, String prenom, String login) {
        if(!nom.equals("")){
            if(this.loginExiste(login) && !Objects.equals(utilisateur.getLogin(), login)){
                throw new UpdateAccountException("Ce nom d'utilisateur est déjà utilisé", utilisateur);
            }
            utilisateur.setNom(nom);
        }
        if(!prenom.equals("")){
            utilisateur.setPrenom(prenom);
        }
        if(!login.equals("")){
            utilisateur.setLogin(login);
        }

        utilisateurRepository.save(utilisateur);
    }

    public void modifierUtilisateurAdmin(Utilisateur newUtilisateur, Long userId) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(userId);

        if(utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            utilisateur.setNom(newUtilisateur.getNom());
            utilisateur.setPrenom(newUtilisateur.getPrenom());
            utilisateur.setLogin(newUtilisateur.getLogin());
            utilisateur.setRole(newUtilisateur.getRole());
            utilisateurRepository.save(utilisateur);
        }
    }

    public List<Utilisateur> listeUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}
