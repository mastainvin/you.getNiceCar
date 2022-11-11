package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.dtos.CarteBleueDto;
import com.jee.yougetnicecar.exceptions.CarteBleueNotFoundException;
import com.jee.yougetnicecar.exceptions.NoMoneyException;
import com.jee.yougetnicecar.models.Produit;
import com.jee.yougetnicecar.models.Utilisateur;
import com.jee.yougetnicecar.services.LogiqueMetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("error", "Veuillez remplir tous les champs.");
            model.addAttribute("montant", model.getAttribute("montant"));

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

    @ExceptionHandler(NoMoneyException.class)
    public String handleNoMoneyException(Model model) {
        model.addAttribute("erreur", "Vous n'avez pas assez d'argent pour payer.");
        model.addAttribute("carteBleueDto", model.getAttribute("carteBleueDto"));

        return "paiements-error";
    }

    @ExceptionHandler(CarteBleueNotFoundException.class)
    public String handleCarteBleueNotFoundException(Model model) {
        model.addAttribute("erreur", "Carte bleue non trouvée.");
        model.addAttribute("carteBleueDto", model.getAttribute("carteBleueDto"));

        return "paiements-error";
    }

}
