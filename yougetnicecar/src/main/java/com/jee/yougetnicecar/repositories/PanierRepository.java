package com.jee.yougetnicecar.repositories;

import com.jee.yougetnicecar.models.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanierRepository extends JpaRepository<Panier, Long> {
    List<Panier> findByUtilisateur_IdOrderByDateDesc(Long id);
}
