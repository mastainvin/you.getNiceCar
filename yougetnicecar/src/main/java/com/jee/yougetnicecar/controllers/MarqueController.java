package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.dtos.MarqueDto;
import com.jee.yougetnicecar.exceptions.MarqueAdminException;
import com.jee.yougetnicecar.exceptions.ResourceNotFoundException;
import com.jee.yougetnicecar.services.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

import static com.jee.yougetnicecar.Utils.checkAdmin;

@Controller
@RequestMapping("/marque")
@SessionAttributes("utilisateur")
public class MarqueController {

    @Autowired
    private MarqueService marqueService;

    @PostMapping("/ajouter")
    public RedirectView ajouterMarque(Model model, @ModelAttribute("marqueDto") MarqueDto marqueDto) {
        checkAdmin(model);

        if (marqueDto.getNom().isEmpty()) {
            throw new MarqueAdminException("Le nom de la marque ne peut pas être vide.");
        }

        marqueService.ajouterMarque(marqueDto);


        return new RedirectView("/produit/admin", true);

    }

    @PostMapping("/modifier/{marqueId}")
    public RedirectView modifierMarque(Model model, @ModelAttribute MarqueDto marqueDto, @PathVariable Long marqueId) {
        checkAdmin(model);

        if (Objects.equals(marqueDto.getNom(), "")) {
            throw new MarqueAdminException("Le nom de la marque ne peut pas être vide.");
        }
        marqueService.modifierMarque(marqueDto, marqueId);

        return new RedirectView("/produit/admin", true);
    }

    @PostMapping("/supprimer/{marqueId}")
    public RedirectView supprimerMarque(Model model, @PathVariable Long marqueId) {
        checkAdmin(model);

        try {
            marqueService.supprimerMarque(marqueId);
        } catch (ResourceNotFoundException e) {
            throw new MarqueAdminException(e.getMessage());
        }

        return new RedirectView("/produit/admin", true);
    }

}
