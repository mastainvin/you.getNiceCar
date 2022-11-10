package com.jee.yougetnicecar.controllers;


import com.jee.yougetnicecar.models.*;
import com.jee.yougetnicecar.repositories.PanierRepository;
import com.jee.yougetnicecar.repositories.ProduitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.jee.yougetnicecar.Utils.checkUser;

@Controller
@RequestMapping("/panier")
public class PanierController {

    private PanierRepository panierRepository;

    private ProduitRepository produitRepository;


    @GetMapping("/anciennescommandes")
    public String voirAnciennesCommandes(@ModelAttribute Utilisateur utilisateur) {



        return "panier";
    }

}
