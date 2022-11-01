package com.jee.yougetnicecar.repositories;

import com.jee.yougetnicecar.models.CarteBleue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteBleueRepository extends JpaRepository<CarteBleue, Long> {
    Optional<CarteBleue> findByNumeroAndDateExpirationAndCryptogrammeAndNomAndPrenom(String numero, String dateExpiration, String cryptogramme, String nom, String prenom);
}