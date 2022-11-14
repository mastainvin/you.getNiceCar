package com.jee.yougetnicecar.services;

import com.jee.yougetnicecar.dtos.ProduitDto;
import com.jee.yougetnicecar.exceptions.ResourceNotFoundException;
import com.jee.yougetnicecar.models.Marque;
import com.jee.yougetnicecar.models.Produit;
import com.jee.yougetnicecar.repositories.MarqueRepository;
import com.jee.yougetnicecar.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;


    @Autowired
    private MarqueRepository marqueRepository;


    protected Produit verifyProduit(Long produitId) throws ResourceNotFoundException {
        Optional<Produit> produit = produitRepository.findById(produitId);
        if (produit.isEmpty()) {
            throw new ResourceNotFoundException("Produit with id " + produitId + " not found.");
        }
        return produit.get();
    }


    public List<Produit> getProduits() {
        return  produitRepository.findAll();
    }

    public List<Marque> getMarques() {
        return  marqueRepository.findAll();
    }

    public void ajouterProduit(ProduitDto produitDto) {
        Produit produit = new Produit();
        produit.setModele(produitDto.getModele());
        produit.setPrix(produitDto.getPrix());
        produit.setMotorisation(produitDto.getMotorisation());
        produit.setMarque(produitDto.getMarque());
        produit.setAnnee(produitDto.getAnnee());
        produit.setStock(produitDto.getStock());
        produit.setImagePath(produitDto.getImagePath());

        produitRepository.save(produit);

    }

    public void supprimerProduit(Long produitId) {
        Produit produit = verifyProduit(produitId);
        produitRepository.delete(produit);
    }

    public void modifierProduit(Produit newProduit, Long produitId) {
        Produit produit = verifyProduit(produitId);
        produit.setModele(newProduit.getModele());
        produit.setPrix(newProduit.getPrix());
        produit.setMotorisation(newProduit.getMotorisation());
        produit.setMarque(newProduit.getMarque());
        produit.setAnnee(newProduit.getAnnee());
        produit.setStock(newProduit.getStock());
        produit.setImagePath(newProduit.getImagePath());
        produitRepository.save(produit);
    }
}
