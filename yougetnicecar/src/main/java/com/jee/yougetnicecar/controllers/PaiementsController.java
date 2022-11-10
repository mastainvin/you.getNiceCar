package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.dtos.CarteBleueDto;
import com.jee.yougetnicecar.models.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequestMapping("/paiement/payer")
public class PaiementsController {

    @PostMapping("/")
    public String payer(Model model) {

        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
        CarteBleueDto carteBleueDto = (CarteBleueDto) model.getAttribute("carteBleueDto");

        if(carteBleueDto != null && utilisateur != null) {
            System.out.println(carteBleueDto.getNom());

        }

        return null;
    }


}
