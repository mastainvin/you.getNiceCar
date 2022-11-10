package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.models.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("utilisateur")
public class AccueilController {

    @GetMapping("/")
    public String accueil(Model model) {
        if (!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }
        return "accueil";
    }

}
