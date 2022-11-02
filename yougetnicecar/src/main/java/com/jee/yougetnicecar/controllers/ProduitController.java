package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.models.Produit;
import com.jee.yougetnicecar.models.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produit")
public class ProduitController {

    // Utilisateur normal

    @GetMapping("/boutique")
    public String voirLaBoutique(@ModelAttribute Utilisateur utilisateur) {
        return null;
    }

    // Utilisateur Admin

    @GetMapping("/admin")
    public String voirLesProduitsAdmin(@ModelAttribute Utilisateur utilisateur) {
        return null;
    }

    @PostMapping("/ajouter")
    public void ajouterProduit(@ModelAttribute Utilisateur utilisateur, @ModelAttribute Produit produit) {

    }

    @DeleteMapping("/supprimer/{produitId}")
    public void supprimer(@ModelAttribute Utilisateur utilisateur, @PathVariable Long produitId) {

    }

    @PutMapping("/modifier/{produitId}")
    public void modifier(@ModelAttribute Utilisateur utilisateur, @PathVariable Long produitId) {

    }
}
