package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.models.Marque;
import com.jee.yougetnicecar.models.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/marque")
public class MarqueController {

    // Admin

    @PostMapping("/ajouter")
    public void ajouterMarque(@ModelAttribute Utilisateur utilisateur, @ModelAttribute Marque marque) {

    }

    @PutMapping("/modifier/{marqueId}")
    public void modifierMarque(@ModelAttribute Utilisateur utilisateur, @ModelAttribute Marque marque, @PathVariable Long marqueId) {

    }

    @DeleteMapping("/supprimer/{marqueId}")
    public void supprimerMarque(@ModelAttribute Utilisateur utilisateur, @PathVariable Long marqueId) {

    }
}
