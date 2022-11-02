package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.models.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panier")
public class PanierController {

    @GetMapping("/anciennescommandes")
    public String voirAnciennesCommandes(@ModelAttribute Utilisateur utilisateur) {
        return null;
    }
}
