package com.jee.yougetnicecar.services;

import com.jee.yougetnicecar.models.EtatPanier;
import com.jee.yougetnicecar.models.Panier;
import com.jee.yougetnicecar.models.Produit;
import com.jee.yougetnicecar.models.Utilisateur;
import com.jee.yougetnicecar.repositories.PanierRepository;
import com.jee.yougetnicecar.repositories.ProduitRepository;
import com.jee.yougetnicecar.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PanierService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public void ajouterAuPanier(Utilisateur utilisateur, Long produitId){

        Optional<Produit> produitOptional = produitRepository.findById(produitId);


        List<Produit> produitList;

        assert utilisateur != null;
        if(produitOptional.isPresent()) {
            if (utilisateur.getPanierCourant() == null) {
                produitList = new ArrayList<>();
                produitOptional.ifPresent(produitList::add);

                Panier panier = new Panier();
                panier.setUtilisateur(utilisateur);

                panier.setProduits(produitList);
                panier.setEtatPanier(EtatPanier.EN_COURS);

                panierRepository.save(panier);
                utilisateur.setPanierCourant(panier);

            } else {
                int count = Math.toIntExact(utilisateur.getPanierCourant().getProduits().stream().filter(produit -> produit.getId().equals(produitId)).count());
                if(count + 1 <= produitOptional.get().getStock()) {
                    produitOptional.ifPresent(value -> utilisateur.getPanierCourant().getProduits().add(value));
                    panierRepository.save(utilisateur.getPanierCourant());
                }
            }
        }

        utilisateurRepository.save(utilisateur);
    }

    public void modifierPanier(Utilisateur utilisateur, int quantite, Long produitId) {
        if (quantite > 0) {

            assert utilisateur != null;

            int count = 0;
            for (int i = 0; i < utilisateur.getPanierCourant().getProduits().size(); i++) {
                if (Objects.equals(utilisateur.getPanierCourant().getProduits().get(i).getId(), produitId)) {
                    count++;
                }
            }

            if (count > quantite) {
                List<Produit> allSameProduits = utilisateur.getPanierCourant().getProduits().stream().filter(produit -> Objects.equals(produit.getId(), produitId)).collect(Collectors.toList());
                for (int i = 0; i < count - quantite; i++) {
                    utilisateur.getPanierCourant().getProduits().remove(allSameProduits.get(i));
                }

            } else if (count < quantite) {
                for (int i = 0; i < quantite - count; i++) {
                    Optional<Produit> produit = produitRepository.findById(produitId);
                    produit.ifPresent(value -> utilisateur.getPanierCourant().getProduits().add(value));
                }
            }


            utilisateurRepository.save(utilisateur);
            panierRepository.save(utilisateur.getPanierCourant());

        } else if (quantite == 0) {
            assert utilisateur != null;
            utilisateur.getPanierCourant().getProduits().removeIf(produit -> Objects.equals(produit.getId(), produitId));


            utilisateurRepository.save(utilisateur);
            panierRepository.save(utilisateur.getPanierCourant());

        }
    }
}
