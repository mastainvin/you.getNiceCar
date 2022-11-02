package com.jee.yougetnicecar.repositories;


import com.jee.yougetnicecar.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByLogin(String login);

    Optional<Utilisateur> findByLoginAndPassword(String login, String password);

}
