package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.dtos.MarqueDto;
import com.jee.yougetnicecar.dtos.ProduitDto;
import com.jee.yougetnicecar.exceptions.ProduitAdminException;
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
import java.util.Optional;

import static com.jee.yougetnicecar.Utils.checkAdmin;

@Controller
@ControllerAdvice
@RequestMapping("/produit")
@SessionAttributes("utilisateur")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;


    @Autowired
    private MarqueRepository marqueRepository;


    protected Produit verifyProduit(Long produitId) throws ResourceNotFoundException {
        Optional<Produit> produit = produitRepository.findById(produitId);
        if(produit.isEmpty()){
            throw new ResourceNotFoundException("Produit with id " + produitId + " not found.");
        }
        return produit.get();
    }

    // Utilisateur normal

    @GetMapping("/boutique")
    public String voirLaBoutique(@ModelAttribute Utilisateur utilisateur) {
        return null;
    }

    // Utilisateur Admin

    @GetMapping("/admin")
    public String voirLesProduitsAdmin(Model model) {
        checkAdmin(model);
        List<Produit> produits = produitRepository.findAll();
        List<Marque> marques = marqueRepository.findAll();

        model.addAttribute("produits", produits);
        model.addAttribute("marques", marques);
        model.addAttribute("motorisations", Motorisation.values());
        model.addAttribute("produitDto", new ProduitDto());
        model.addAttribute("marqueDto", new MarqueDto());
        model.addAttribute("produit", new Produit());
        model.addAttribute("marque", new Marque());

        return "admin_produits";
    }

    @PostMapping("/ajouter")
    public RedirectView ajouterProduit(Model model, @ModelAttribute("produitDto") ProduitDto produitDto) {


        if(produitDto.getModele().isEmpty() || produitDto.getModele() == null || produitDto.getMotorisation() == null || produitDto.getPrix() == null || produitDto.getMarque() == null || produitDto.getStock() == null || produitDto.getStock() < 0 || produitDto.getPrix() < 0 || produitDto.getImagePath() == null){
            throw new ProduitAdminException("Tous les champs doivent être remplis.");
        }

        checkAdmin(model);

        Produit produit = new Produit();
        produit.setModele(produitDto.getModele());
        produit.setPrix(produitDto.getPrix());
        produit.setMotorisation(produitDto.getMotorisation());
        produit.setMarque(produitDto.getMarque());
        produit.setAnnee(produitDto.getAnnee());
        produit.setStock(produitDto.getStock());

        // TODO : Ajouter l'image créer un chemin vers l'image et l'enregistrer dans la base de donnée

        produit.setImagePath(produitDto.getImagePath());

        produitRepository.save(produit);

        return new RedirectView("/produit/admin", true);
    }



    @PostMapping("/supprimer/{produitId}")
    public RedirectView supprimer(Model model, @PathVariable Long produitId) {
        checkAdmin(model);

        try {
            Produit produit = verifyProduit(produitId);
            produitRepository.delete(produit);
        } catch (ResourceNotFoundException e) {
            throw new ProduitAdminException("Produit avec l'id \" + produitId + \" non trouvé");
        }

        return new RedirectView("/produit/admin", true);
    }

    @PostMapping("/modifier/{produitId}")
    public RedirectView modifier(Model model, @PathVariable Long produitId, @ModelAttribute("produit") Produit newProduit) {
        checkAdmin(model);

        if(newProduit.getModele().isEmpty() || newProduit.getModele() == null || newProduit.getMotorisation() == null || newProduit.getPrix() == null || newProduit.getMarque() == null || newProduit.getStock() == null || newProduit.getStock() < 0 || newProduit.getPrix() < 0 || newProduit.getImagePath() == null){
            throw new ProduitAdminException("Tous les champs doivent être remplis.");

        }

        Produit produit = verifyProduit(produitId);
        produit.setModele(newProduit.getModele());
        produit.setPrix(newProduit.getPrix());
        produit.setMotorisation(newProduit.getMotorisation());
        produit.setMarque(newProduit.getMarque());
        produit.setAnnee(newProduit.getAnnee());
        produit.setStock(newProduit.getStock());
        produit.setImagePath(newProduit.getImagePath());
        produitRepository.save(produit);

        return new RedirectView("/produit/admin", true);
    }



    // Exceptions

    @ExceptionHandler(ProduitAdminException.class)
    public ModelAndView productAdminException(ProduitAdminException e) {
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
        modelAndView.setViewName("admin_produits-error");
        return modelAndView;

    }

}
