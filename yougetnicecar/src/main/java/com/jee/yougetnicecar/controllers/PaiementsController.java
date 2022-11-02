package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.dtos.CarteBleueDto;
import com.jee.yougetnicecar.models.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paiement")
public class PaiementsController {

    @PostMapping("/")
    public String payer(@ModelAttribute Utilisateur utilisateur, @ModelAttribute CarteBleueDto carteBleueDto) {
        return null;
    }


}
