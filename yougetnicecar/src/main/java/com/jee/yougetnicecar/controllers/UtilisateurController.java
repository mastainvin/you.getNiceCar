package com.jee.yougetnicecar.controllers;


import com.jee.yougetnicecar.dtos.UtilisateurConnexionDto;
import com.jee.yougetnicecar.dtos.UtilisateurInscriptionDto;
import com.jee.yougetnicecar.exceptions.*;
import com.jee.yougetnicecar.models.*;
import com.jee.yougetnicecar.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

import static com.jee.yougetnicecar.Utils.checkAdmin;
import static com.jee.yougetnicecar.Utils.checkUser;

@Controller
@ControllerAdvice
@RequestMapping("/")
@SessionAttributes("utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("/")
    public String accueil(Model model) {
        if (!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }
        return "accueil";
    }

    @GetMapping("/connexion")
    public String connexion(Model model) {
        if (!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }
        model.addAttribute("utilisateurConnexionDto", new UtilisateurConnexionDto());
        return "connexion";
    }

    @PostMapping("/connexion")
    public RedirectView connexion(@ModelAttribute("utilisateurConnexionDto") UtilisateurConnexionDto utilisateurConnexionDto, RedirectAttributes attributes) {
        attributes.addFlashAttribute("utilisateur", utilisateurService.connexion(utilisateurConnexionDto));
        return new RedirectView("/", true);
    }


    @GetMapping("/inscription")
    public String inscription(Model model) {
        if (!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }
        model.addAttribute("utilisateurInscriptionDto", new UtilisateurInscriptionDto());
        return "inscription";
    }

    @PostMapping("/inscription")
    public RedirectView inscription(@ModelAttribute("utilisateurInscriptionDto") UtilisateurInscriptionDto utilisateurInscriptionDto, RedirectAttributes attributes) {
        if (Objects.equals(utilisateurInscriptionDto.getUsername(), "") || Objects.equals(utilisateurInscriptionDto.getNom(), "") || Objects.equals(utilisateurInscriptionDto.getPassword(), "") || Objects.equals(utilisateurInscriptionDto.getRepeatPassword(), "") || Objects.equals(utilisateurInscriptionDto.getPrenom(), "")) {
            throw new InscriptionException("Veuillez remplir tous les champs", utilisateurInscriptionDto);
        }


        if (utilisateurService.loginExiste(utilisateurInscriptionDto.getUsername())) {
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
        if (!model.containsAttribute("utilisateur")) {
            model.addAttribute(new Utilisateur());
        }

        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");

        assert utilisateur != null;
        if (utilisateur.getId() != null) {
            utilisateur.setId(null);
            utilisateur.setPanierCourant(null);
            utilisateur.setRole(null);
            utilisateur.setPrenom(null);
            utilisateur.setNom(null);
            utilisateur.setLogin(null);
        }

        return new RedirectView("/", true);
    }

    @GetMapping("/compte")
    public String compte(Model model) {
        checkUser(model);
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");

        assert utilisateur != null;

        model.addAttribute("commandes", utilisateurService.getCommandes(utilisateur));
        return "compte";
    }

    @PostMapping("/compte/modifier/{id}")
    public RedirectView modifierCompte(Model model, @PathVariable Long id, @RequestParam String nom, @RequestParam String prenom, @RequestParam String login) {
        checkUser(model);
        Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
        assert utilisateur != null;

        // Si l'utilisateur veut modifier son propre compte
        if(!Objects.equals(utilisateur.getId(), id)){
            return new RedirectView("/compte", true);
        }

        utilisateurService.modifierCompte(utilisateur, nom, prenom, login);

        return new RedirectView("/compte", true);
    }

    // Admin

    @PostMapping("/admin/users/update/{userId}")
    public RedirectView modifierUtilisateur(@ModelAttribute("bdd_utilisateur") Utilisateur newUtilisateur, @PathVariable Long userId, Model model) {
        checkAdmin(model);
        utilisateurService.modifierUtilisateurAdmin(newUtilisateur, userId);
        return new RedirectView("/admin/users", true);
    }

    @GetMapping("/admin/users")
    public String voirListeUtilisateurs(Model model) {
        checkAdmin(model);

        model.addAttribute("utilisateurs", utilisateurService.listeUtilisateurs());
        model.addAttribute("bdd_utilisateur", new Utilisateur());
        model.addAttribute("roles", Role.values());

        return "admin_utilisateurs";
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

    @ExceptionHandler(NotConnectedException.class)
    public RedirectView notConnectedException(NotConnectedException e) {
        return new RedirectView("/", true);
    }

    @ExceptionHandler(UpdateAccountException.class)
    public ModelAndView updateAccountException(UpdateAccountException e) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("erreur", e.getMessage());
        modelAndView.addObject("commandes", utilisateurService.getCommandes(e.getUtilisateur()));
        modelAndView.addObject("utilisateur", e.getUtilisateur());
        modelAndView.setViewName("compte-error");
        return modelAndView;
    }
}