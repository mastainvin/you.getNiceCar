package com.jee.yougetnicecar.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


import com.jee.yougetnicecar.dtos.CarteBleueDto;
import com.jee.yougetnicecar.dtos.UtilisateurConnexionDto;
import com.jee.yougetnicecar.dtos.UtilisateurInscriptionDto;
import com.jee.yougetnicecar.exceptions.*;
import com.jee.yougetnicecar.models.EtatPanier;
import com.jee.yougetnicecar.models.Panier;
import com.jee.yougetnicecar.models.Produit;
import com.jee.yougetnicecar.models.Utilisateur;
import com.jee.yougetnicecar.repositories.PanierRepository;
import com.jee.yougetnicecar.repositories.ProduitRepository;
import com.jee.yougetnicecar.repositories.UtilisateurRepository;
import com.jee.yougetnicecar.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;
import java.util.stream.Collectors;

import static com.jee.yougetnicecar.Utils.checkUser;

@Controller
@ControllerAdvice
@RequestMapping("/")
@SessionAttributes("utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    PanierRepository panierRepository;

   @Autowired
   ProduitRepository produitRepository;


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

    @GetMapping("/ajouter/{produitId}")
    public RedirectView ajouterAuPanier(Model model, @PathVariable Long produitId) {
        checkUser(model);

        Optional<Produit> produit = produitRepository.findById(produitId);

        Utilisateur utilisateur = (Utilisateur)model.getAttribute("utilisateur");

        List<Produit> produitList;

        assert utilisateur != null;
        if(utilisateur.getPanierCourant() == null)
       {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            produitList = new ArrayList<>();
            produit.ifPresent(produitList::add);

            Panier panier = new Panier();
            panier.setUtilisateur(utilisateur);
            panier.setDate(LocalDate.parse(dtf.format(now)));
            panier.setProduits(produitList);
            panier.setEtatPanier(EtatPanier.EN_COURS);

       }
       else {
            produit.ifPresent(value -> utilisateur.getPanierCourant().getProduits().add(value));
       }
        utilisateurRepository.save(utilisateur);
        return new RedirectView("/produit/boutique", true);
    }

    @GetMapping("/modifierpanier/{produitId}/{quantite}")
    public RedirectView modifPanier(Model model, @PathVariable int quantite,@PathVariable Long produitId) {
        checkUser(model);
        if(quantite > 0){

            Utilisateur utilisateur = (Utilisateur)model.getAttribute("utilisateur");
            assert utilisateur != null;

            int count = 0;
            for (int i = 0; i < utilisateur.getPanierCourant().getProduits().size(); i++) {
                if(Objects.equals(utilisateur.getPanierCourant().getProduits().get(i).getId(), produitId)){
                    count++;
                }
            }

            if(count > quantite) {
                List<Produit> allSameProduits = utilisateur.getPanierCourant().getProduits().stream().filter(produit -> Objects.equals(produit.getId(), produitId)).collect(Collectors.toList());
                for (int i = 0; i < count - quantite; i++) {
                    utilisateur.getPanierCourant().getProduits().remove(allSameProduits.get(i));
                }

            }
            else if(count < quantite){
                for(int i = 0; i < quantite - count; i++) {
                    Optional<Produit> produit = produitRepository.findById(produitId);
                    produit.ifPresent(value -> utilisateur.getPanierCourant().getProduits().add(value));
                }
            }
            utilisateurRepository.save(utilisateur);
        } else if (quantite == 0) {
            Utilisateur utilisateur = (Utilisateur)model.getAttribute("utilisateur");
            assert utilisateur != null;
            utilisateur.getPanierCourant().getProduits().removeIf(produit -> Objects.equals(produit.getId(), produitId));
            utilisateurRepository.save(utilisateur);
        }
        return new RedirectView("/panier", true);
    }

    @GetMapping("/panier")
    public String voirPanier(Model model) {
        checkUser(model);
        Utilisateur utilisateur = (Utilisateur)model.getAttribute("utilisateur");

        HashMap<Produit, Integer> produitQuantite = new HashMap<>();
        assert utilisateur != null;
        for (Produit produit : utilisateur.getPanierCourant().getProduits()) {
            boolean found = false;
            for(Produit produit1: produitQuantite.keySet()){
                if(Objects.equals(produit.getId(), produit1.getId())){
                    produitQuantite.put(produit1, produitQuantite.get(produit1) + 1);
                    found = true;
                    break;
                }
            }
            if(!found){
                produitQuantite.put(produit, 1);
            }
        }

        model.addAttribute("panier_courant", produitQuantite);
        return "panier";
    }

    @GetMapping("/paiement")
    public String paiement(Model model) {
        checkUser(model);

        CarteBleueDto carteBleueDto = new CarteBleueDto();
        model.addAttribute("carteBleueDto", carteBleueDto);

        return "paiements";
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

    @ExceptionHandler(NotUserException.class)
    public RedirectView notConnectedException(NotUserException e) { return new RedirectView("/", true); }
}