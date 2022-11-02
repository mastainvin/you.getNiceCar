package com.jee.yougetnicecar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminProduitsController {

    @GetMapping("/")
    public String admin_produits(Model model) {
        model.addAttribute("title", "Admin Produits");
        return "admin_produits";
    }
}
