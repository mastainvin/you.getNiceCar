package com.jee.yougetnicecar.repositories;

import com.jee.yougetnicecar.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
