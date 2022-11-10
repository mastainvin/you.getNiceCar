package com.jee.yougetnicecar.services;

import com.jee.yougetnicecar.exceptions.CarteBleueNotFoundException;
import com.jee.yougetnicecar.exceptions.NoMoneyException;
import com.jee.yougetnicecar.models.CarteBleue;
import com.jee.yougetnicecar.models.EtatPanier;
import com.jee.yougetnicecar.models.Utilisateur;
import com.jee.yougetnicecar.repositories.CarteBleueRepository;
import com.jee.yougetnicecar.repositories.PanierRepository;
import com.jee.yougetnicecar.repositories.ProduitRepository;
import com.jee.yougetnicecar.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogiqueMetierService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private CarteBleueRepository carteBleueRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private PanierRepository panierRepository;

    public void payer(Utilisateur utilisateur, String numeroCarte, String dateExpiration, String cryptogramme, String nom, String prenom, Integer montant) throws NoMoneyException, CarteBleueNotFoundException {
        Optional<CarteBleue> carteBleue = carteBleueRepository.findByNumeroAndDateExpirationAndCryptogrammeAndNomAndPrenom(numeroCarte, dateExpiration, cryptogramme, nom, prenom);

        if (carteBleue.isPresent()) {
            if (carteBleue.get().getSolde() >= montant) {

                // Modifie le solde de la carte bleue
                carteBleue.get().setSolde(carteBleue.get().getSolde() - montant);
                carteBleueRepository.save(carteBleue.get());

                // Modifie le stock de chaque produit du panier
                for (int i = 0; i < utilisateur.getPanierCourant().getProduits().size(); i++) {
                    utilisateur.getPanierCourant().getProduits().get(i).setStock(utilisateur.getPanierCourant().getProduits().get(i).getStock() - 1);
                    produitRepository.save(utilisateur.getPanierCourant().getProduits().get(i));
                }

                // Detach panier
                utilisateur.getPanierCourant().setEtatPanier(EtatPanier.PAYE);
                panierRepository.save(utilisateur.getPanierCourant());

                // Modifie le panier courant de l'utilisateur
                utilisateur.setPanierCourant(null); // TODO : Vérifier si ça marche (possibilité d'erreur pour reremplir le panier après)
                utilisateurRepository.save(utilisateur);
            } else {
                throw new NoMoneyException("Pas assez d'argent sur la carte bleue");
            }
        } else {
            throw new CarteBleueNotFoundException("Carte bleue non trouvée");
        }
    }
}