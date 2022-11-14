package com.jee.yougetnicecar.controllers;


import com.jee.yougetnicecar.models.*;
import com.jee.yougetnicecar.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import java.util.*;

import static com.jee.yougetnicecar.Utils.checkUser;

@Controller
@RequestMapping("/panier")
@SessionAttributes("utilisateur")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @GetMapping("/ajouter/{produitId}")
    public RedirectView ajouterAuPanier(Model model, @PathVariable Long produitId) {
        checkUser(model);
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
        panierService.ajouterAuPanier(utilisateur, produitId);
        return new RedirectView("/produit/boutique", true);
    }

    @GetMapping("/modifier/{produitId}/{quantite}")
    public RedirectView modifierPanier(Model model, @PathVariable int quantite, @PathVariable Long produitId) {
        checkUser(model);
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
        panierService.modifierPanier(utilisateur, quantite, produitId);
        return new RedirectView("/panier", true);
    }

    @GetMapping("")
    public String voirPanier(Model model) {
        checkUser(model);
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");

        HashMap<Produit, Integer> produitQuantite = new HashMap<>();
        Integer total = 0;
        assert utilisateur != null;

        if(!(utilisateur.getPanierCourant() == null)){
            for (Produit produit : utilisateur.getPanierCourant().getProduits()) {
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
        }

        model.addAttribute("total", total);
        model.addAttribute("panier_courant", produitQuantite);
        return "panier";
    }

}
