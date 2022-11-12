package com.jee.yougetnicecar.repositories;

import com.jee.yougetnicecar.models.EtatPanier;
import com.jee.yougetnicecar.models.Panier;
import com.jee.yougetnicecar.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanierRepository extends JpaRepository<Panier, Long> {
    List<Panier> findByUtilisateurAndEtatPanierOrderByDateDesc(Utilisateur utilisateur, EtatPanier etatPanier);
}
