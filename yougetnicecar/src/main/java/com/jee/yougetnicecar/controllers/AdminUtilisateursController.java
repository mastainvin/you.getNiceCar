package com.jee.yougetnicecar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminUtilisateursController {

    @GetMapping("/")
    public String admin_utilisateurs(Model model) {
        model.addAttribute("title", "Admin Utilisateurs");
        return "admin_utilisateurs";
    }
}
