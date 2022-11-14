package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.dtos.CarteBleueDto;
import com.jee.yougetnicecar.exceptions.PaiementException;
import com.jee.yougetnicecar.models.Produit;
import com.jee.yougetnicecar.models.Utilisateur;
import com.jee.yougetnicecar.services.LogiqueMetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.jee.yougetnicecar.Utils.checkUser;

@Controller
@ControllerAdvice
@RequestMapping("/paiement")
@SessionAttributes("utilisateur")
public class PaiementsController {


    @Autowired
    private LogiqueMetierService logiqueMetierService;

    @GetMapping
    public String paiement(Model model) {
        checkUser(model);

        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
        assert utilisateur != null;
        model.addAttribute("montant", utilisateur.getPanierCourant().getProduits().stream().mapToInt(Produit::getPrix).sum());
        model.addAttribute("carteBleueDto", new CarteBleueDto());

        return "paiements";
    }

    @PostMapping("/payer")
    public String payer(Model model, @ModelAttribute("carteBleueDto") CarteBleueDto carteBleueDto) {
        checkUser(model);

        if(carteBleueDto.getNumero() == null || carteBleueDto.getDateExpiration() == null || carteBleueDto.getCryptogramme() == null || carteBleueDto.getNom() == null || carteBleueDto.getPrenom() == null) {
            model.addAttribute("erreur", "Veuillez remplir tous les champs.");
            model.addAttribute("montant", model.getAttribute("montant"));
            model.addAttribute("utilisateur", model.getAttribute("utilisateur"));
            model.addAttribute("carteBleueDto", model.getAttribute("carteBleueDto"));
            return "paiements-error";
        }

        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");

        if (utilisateur != null) {
           logiqueMetierService.payer(carteBleueDto, utilisateur);
        } else {
            model.addAttribute("erreur", "Erreur lors du paiement");
            return "paiements-error";
        }

        return "paiement_accepte";
    }



    @ExceptionHandler(PaiementException.class)
    public ModelAndView carteBleueException(PaiementException e) {
        ModelAndView modelAndView = new ModelAndView("paiements-error");
        modelAndView.addObject("erreur", e.getMessage());
        modelAndView.addObject("carteBleueDto", e.getCarteBleueDto());
        modelAndView.addObject("montant", e.getMontant());
        modelAndView.addObject("utilisateur", e.getUtilisateur());

        return modelAndView;
    }

}
