package com.jee.yougetnicecar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PanierController {

    @GetMapping("/")
    public String panier(Model model) {
        model.addAttribute("title", "Panier");
        return "panier";
    }
}
