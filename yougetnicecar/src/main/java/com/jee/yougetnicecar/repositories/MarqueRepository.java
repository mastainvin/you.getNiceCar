package com.jee.yougetnicecar.repositories;

import com.jee.yougetnicecar.models.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarqueRepository extends JpaRepository<Marque, Long> {
}