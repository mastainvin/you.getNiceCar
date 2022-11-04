package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.dtos.MarqueDto;
import com.jee.yougetnicecar.dtos.ProduitDto;
import com.jee.yougetnicecar.exceptions.MarqueAdminException;
import com.jee.yougetnicecar.exceptions.ResourceNotFoundException;
import com.jee.yougetnicecar.models.*;
import com.jee.yougetnicecar.repositories.MarqueRepository;
import com.jee.yougetnicecar.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.jee.yougetnicecar.Utils.checkAdmin;

@Controller
@RequestMapping("/marque")
@SessionAttributes("utilisateur")
public class MarqueController {


    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private ProduitRepository produitRepository;
    // Admin

    protected Marque verifyMarque(Long marqueId) throws ResourceNotFoundException {
        Optional<Marque> marque = marqueRepository.findById(marqueId);
        if(marque.isEmpty()){
            throw new ResourceNotFoundException("Marque with id " + marqueId + " not found.");
        }
        return marque.get();
    }

    @PostMapping("/ajouter")
    public RedirectView ajouterMarque(Model model, @ModelAttribute("marqueDto") MarqueDto marqueDto) {
        checkAdmin(model);

        if(marqueDto.getNom().isEmpty()){
            throw new MarqueAdminException("Le nom de la marque ne peut pas être vide.");
        }
        Marque marque = new Marque();
        marque.setNom(marqueDto.getNom());
        marqueRepository.save(marque);


        return new RedirectView("/produit/admin", true);

    }

    @PostMapping("/modifier/{marqueId}")
    public RedirectView modifierMarque(Model model, @ModelAttribute MarqueDto marqueDto, @PathVariable Long marqueId) {
        checkAdmin(model);

        if(Objects.equals(marqueDto.getNom(), "")) {
            throw new MarqueAdminException("Le nom de la marque ne peut pas être vide.");
        }
        Marque marque = verifyMarque(marqueId);
        marque.setNom(marqueDto.getNom());
        marqueRepository.save(marque);

        return new RedirectView("/produit/admin", true);
    }

    @PostMapping("/supprimer/{marqueId}")
    public RedirectView supprimerMarque(Model model, @PathVariable Long marqueId) {
        checkAdmin(model);

        try {
            Marque marque = verifyMarque(marqueId);
            marqueRepository.delete(marque);
        } catch (ResourceNotFoundException e) {
            throw new MarqueAdminException(e.getMessage());
        }

        return new RedirectView("/produit/admin", true);
    }


    @ExceptionHandler(MarqueAdminException.class)
    public ModelAndView productAdminException(MarqueAdminException e) {
        final ModelAndView modelAndView = new ModelAndView();
        List<Produit> produits = produitRepository.findAll();
        List<Marque> marques = marqueRepository.findAll();

        modelAndView.addObject("produits", produits);
        modelAndView.addObject("marques", marques);
        modelAndView.addObject("motorisations", Motorisation.values());
        modelAndView.addObject("produitDto", new ProduitDto());
        modelAndView.addObject("marqueDto", new MarqueDto());
        modelAndView.addObject("produit", new Produit());
        modelAndView.addObject("marque", new Marque());

        modelAndView.addObject("erreur", e.getMessage());
        modelAndView.setViewName("admin_marques-error");
        return modelAndView;
    }

}
