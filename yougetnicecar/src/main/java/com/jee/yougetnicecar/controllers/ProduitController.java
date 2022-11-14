package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.dtos.MarqueDto;
import com.jee.yougetnicecar.dtos.ProduitDto;
import com.jee.yougetnicecar.exceptions.MarqueAdminException;
import com.jee.yougetnicecar.exceptions.ProduitAdminException;
import com.jee.yougetnicecar.exceptions.ResourceNotFoundException;
import com.jee.yougetnicecar.models.*;
import com.jee.yougetnicecar.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Objects;

import static com.jee.yougetnicecar.Utils.checkAdmin;

@Controller
@ControllerAdvice
@RequestMapping("/produit")
@SessionAttributes("utilisateur")
public class ProduitController {


    @Autowired
    private ProduitService produitService;


    // Utilisateur normal

    @GetMapping("/boutique")
    public String voirLaBoutique(Model model) {
        model.addAttribute("produits", produitService.getProduits());
        model.addAttribute("marques", produitService.getMarques());
        return "boutique";
    }

    // Utilisateur Admin

    @GetMapping("/admin")
    public String voirLesProduitsAdmin(Model model) {
        checkAdmin(model);
        List<Produit> produits = produitService.getProduits();
        List<Marque> marques = produitService.getMarques();
        model.addAttribute("produits", produits);
        model.addAttribute("marques", marques);
        model.addAttribute("motorisations", Motorisation.values());
        model.addAttribute("produitDto", new ProduitDto());
        model.addAttribute("marqueDto", new MarqueDto());
        model.addAttribute("produit", new ProduitDto());
        model.addAttribute("marque", new Marque());
        return "admin_produits";
    }

    @PostMapping("/ajouter")
    public RedirectView ajouterProduit(Model model, @ModelAttribute("produitDto") ProduitDto produitDto) {
        checkAdmin(model);
        if (produitDto.getModele().isEmpty() || Objects.equals(produitDto.getModele(), "") || produitDto.getMotorisation() == null || produitDto.getPrix() == null || produitDto.getMarque() == null || produitDto.getStock() == null || produitDto.getStock() < 0 || produitDto.getPrix() < 0 || Objects.equals(produitDto.getImagePath(), "")) {
            throw new ProduitAdminException("Tous les champs doivent être remplis.");
        }
        checkAdmin(model);
        produitService.ajouterProduit(produitDto);
        return new RedirectView("/produit/admin", true);
    }


    @PostMapping("/supprimer/{produitId}")
    public RedirectView supprimer(Model model, @PathVariable Long produitId) {
        checkAdmin(model);
        try {
           produitService.supprimerProduit(produitId);
        } catch (ResourceNotFoundException e) {
            throw new ProduitAdminException("Produit avec l'id \" + produitId + \" non trouvé");
        }
        return new RedirectView("/produit/admin", true);
    }

    @PostMapping("/modifier/{produitId}")
    public RedirectView modifier(Model model, @PathVariable Long produitId, @ModelAttribute("produit") Produit newProduit) {
        checkAdmin(model);
        if (newProduit.getModele().isEmpty() || newProduit.getModele() == null || newProduit.getMotorisation() == null || newProduit.getPrix() == null || newProduit.getMarque() == null || newProduit.getStock() == null || newProduit.getStock() < 0 || newProduit.getPrix() < 0 /**|| newProduit.getImagePath() == null**/) {
            throw new ProduitAdminException("Tous les champs doivent être remplis.");
        }
        produitService.modifierProduit(newProduit, produitId);
        return new RedirectView("/produit/admin", true);
    }


    // Exceptions

    @ExceptionHandler(ProduitAdminException.class)
    public ModelAndView produitAdminException(ProduitAdminException e) {
        ModelAndView modelAndView = initialiserModelAndView();
        modelAndView.addObject("erreur", e.getMessage());
        modelAndView.setViewName("admin_produits-error");
        return modelAndView;

    }

    @ExceptionHandler(MarqueAdminException.class)
    public ModelAndView marqueAdminException(MarqueAdminException e) {
        ModelAndView modelAndView = initialiserModelAndView();
        modelAndView.addObject("erreur", e.getMessage());
        modelAndView.setViewName("admin_marques-error");
        return modelAndView;
    }


    private ModelAndView initialiserModelAndView() {
        final ModelAndView modelAndView = new ModelAndView();
        List<Produit> produits = produitService.getProduits();
        List<Marque> marques = produitService.getMarques();

        modelAndView.addObject("produits", produits);
        modelAndView.addObject("marques", marques);
        modelAndView.addObject("motorisations", Motorisation.values());
        modelAndView.addObject("produitDto", new ProduitDto());
        modelAndView.addObject("marqueDto", new MarqueDto());
        modelAndView.addObject("produit", new Produit());
        modelAndView.addObject("marque", new Marque());

        return modelAndView;
    }
}
