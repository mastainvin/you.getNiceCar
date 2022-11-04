package com.jee.yougetnicecar.controllers;

import com.jee.yougetnicecar.dtos.UtilisateurConnexionDto;
import com.jee.yougetnicecar.dtos.UtilisateurInscriptionDto;
import com.jee.yougetnicecar.exceptions.ConnexionException;
import com.jee.yougetnicecar.exceptions.InscriptionException;
import com.jee.yougetnicecar.exceptions.NotAdminException;
import com.jee.yougetnicecar.exceptions.ResourceNotFoundException;
import com.jee.yougetnicecar.models.Utilisateur;
import com.jee.yougetnicecar.repositories.UtilisateurRepository;
import com.jee.yougetnicecar.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;
import java.util.Optional;

@Controller
@ControllerAdvice
@RequestMapping("/")
@SessionAttributes("utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    UtilisateurService utilisateurService;
    protected Utilisateur verifyUtilisateur(Long userID) throws ResourceNotFoundException {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(userID);
        if(utilisateur.isEmpty()){
            throw new ResourceNotFoundException("Utilisateur with id " + userID + " not found.");
        }
        return utilisateur.get();
    }
    @GetMapping("/connexion")
    public String connexion(Model model) {
        if(!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }
        model.addAttribute("utilisateurConnexionDto", new UtilisateurConnexionDto());
        return "connexion";
    }

    @PostMapping("/connexion")
    public RedirectView connexion(@ModelAttribute("utilisateurConnexionDto") UtilisateurConnexionDto utilisateurConnexionDto, Model model, RedirectAttributes attributes) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByLoginAndPassword(utilisateurConnexionDto.getUsername(), utilisateurConnexionDto.getPassword());

        if(utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            attributes.addFlashAttribute("utilisateur", utilisateur);
        } else {
            throw new ConnexionException("Utilisateur ou mot de passe incorrect", utilisateurConnexionDto);
        }

        return new RedirectView("/", true);
    }



    @GetMapping("/inscription")
    public String inscription(Model model) {
        if(!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }
        model.addAttribute("utilisateurInscriptionDto", new UtilisateurInscriptionDto());
        return "inscription";
    }

    @PostMapping("/inscription")
    public RedirectView inscription(@ModelAttribute("utilisateurInscriptionDto") UtilisateurInscriptionDto utilisateurInscriptionDto, RedirectAttributes attributes) {
        if(Objects.equals(utilisateurInscriptionDto.getUsername(), "") || Objects.equals(utilisateurInscriptionDto.getNom(), "") || Objects.equals(utilisateurInscriptionDto.getPassword(), "") || Objects.equals(utilisateurInscriptionDto.getRepeatPassword(), "") || Objects.equals(utilisateurInscriptionDto.getPrenom(), "")) {
            throw new InscriptionException("Veuillez remplir tous les champs", utilisateurInscriptionDto);
        }

        Optional<Utilisateur> nomUtiliseUtilisateur = utilisateurRepository.findByLogin(utilisateurInscriptionDto.getUsername());

        if(nomUtiliseUtilisateur.isPresent()) {
           throw new InscriptionException("Nom d'utilisateur déjà pris.", utilisateurInscriptionDto);
        } else if (!utilisateurInscriptionDto.getPassword().equals(utilisateurInscriptionDto.getRepeatPassword())) {
           throw new InscriptionException("Les mots de passes ne correspondent pas", utilisateurInscriptionDto);
        } else {
            Utilisateur utilisateur = utilisateurService.creerUtilisateur(utilisateurInscriptionDto);
            attributes.addFlashAttribute("utilisateur", utilisateur);
        }

        return new RedirectView("/", true);
    }



    @GetMapping("/deconnexion")
    public RedirectView deconnexion(Model model, RedirectAttributes attributes) {
        if(!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }

        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");

        assert utilisateur != null;
        if(utilisateur.getId() != null) {
            utilisateur.setId(null);
            utilisateur.setPanierCourant(null);
            utilisateur.setRole(null);
            utilisateur.setPrenom(null);
            utilisateur.setNom(null);
            utilisateur.setLogin(null);
        }

        return new RedirectView("/", true);
    }

    @PostMapping("/panier/ajouter/{produitId}")
    public void ajouterAuPanier(@PathVariable Long produitId, Model model) {
        if(!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }
    }

    @GetMapping("/panier")
    public String voirPanier(Model model) {
        if(!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }

        return null;
    }

    // Admin

    @PutMapping("/update/{userId}")
    public void modifierUtilisateur(@ModelAttribute Utilisateur newUtilisateur, @PathVariable Long userId, Model model) {
        if(!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }
    }

    @GetMapping("/all")
    public String voirListeUtilisateurs(Model model) {
        if(!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }
        return null;
    }



    // Exceptions

    @ExceptionHandler(ConnexionException.class)
    public ModelAndView connexionException(ConnexionException e) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("utilisateurConnexionDto", e.getUtilisateurConnexionDto());
        modelAndView.addObject("erreur", e.getMessage());
        modelAndView.setViewName("connexion-error");
        return modelAndView;
    }


    @ExceptionHandler(InscriptionException.class)
    public ModelAndView inscriptionException(InscriptionException e) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("utilisateurInscriptionDto", e.getUtilisateurInscriptionDto());
        modelAndView.addObject("erreur", e.getMessage());
        modelAndView.setViewName("inscription-error");
        return modelAndView;
    }

    @ExceptionHandler(NotAdminException.class)
    public RedirectView notAdminException(NotAdminException e) {
        return new RedirectView("/", true);
    }

}