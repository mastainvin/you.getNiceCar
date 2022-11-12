package com.jee.yougetnicecar.services;

import com.jee.yougetnicecar.dtos.CarteBleueDto;
import com.jee.yougetnicecar.exceptions.PaiementException;
import com.jee.yougetnicecar.models.*;
import com.jee.yougetnicecar.repositories.CarteBleueRepository;
import com.jee.yougetnicecar.repositories.PanierRepository;
import com.jee.yougetnicecar.repositories.ProduitRepository;
import com.jee.yougetnicecar.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;
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

    public void payer(CarteBleueDto carteBleueDto, Utilisateur utilisateur) {
        Optional<CarteBleue> carteBleue = carteBleueRepository.findByNumeroAndDateExpirationAndCryptogrammeAndNomAndPrenom(carteBleueDto.getNumero(), carteBleueDto.getDateExpiration(), carteBleueDto.getCryptogramme(), carteBleueDto.getNom(), carteBleueDto.getPrenom());

        Integer montant = utilisateur.getPanierCourant().getProduits().stream().mapToInt(Produit::getPrix).sum();
        if (carteBleue.isPresent()) {
            if (carteBleue.get().getSolde() >= montant) {

                // Modifie le solde de la carte bleue
                carteBleue.get().setSolde(carteBleue.get().getSolde() - montant);
                carteBleueRepository.save(carteBleue.get());

                // Modifie le stock de chaque produit du panier
                HashMap<Produit, Integer> produitQuantite = new HashMap<>();

                if(!(utilisateur.getPanierCourant() == null)){
                    for (Produit produit : utilisateur.getPanierCourant().getProduits()) {
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
                }

                for(Produit produit : produitQuantite.keySet()){
                    produit.setStock(produit.getStock() - produitQuantite.get(produit));
                    produitRepository.save(produit);
                }


                Panier panier = utilisateur.getPanierCourant();


                // Modifie le panier courant de l'utilisateur
                utilisateur.setPanierCourant(null); // TODO : Vérifier si ça marche (possibilité d'erreur pour reremplir le panier après)
                utilisateurRepository.save(utilisateur);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                panier.setDate(LocalDate.parse(dtf.format(now)));
                panier.setEtatPanier(EtatPanier.PAYE);
                panier.setUtilisateur(utilisateur);
                panierRepository.save(panier);

            } else {
                throw new PaiementException("Pas assez d'argent sur la carte bleue", utilisateur, montant, carteBleueDto);
            }
        } else {
            throw new PaiementException("Carte bleue non trouvée", utilisateur, montant, carteBleueDto);
        }
    }
}