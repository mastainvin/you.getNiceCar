package com.jee.yougetnicecar.services;

import com.jee.yougetnicecar.dtos.MarqueDto;
import com.jee.yougetnicecar.exceptions.ResourceNotFoundException;
import com.jee.yougetnicecar.models.Marque;
import com.jee.yougetnicecar.repositories.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;


    protected Marque verifyMarque(Long marqueId) throws ResourceNotFoundException {
        Optional<Marque> marque = marqueRepository.findById(marqueId);
        if (marque.isEmpty()) {
            throw new ResourceNotFoundException("Marque with id " + marqueId + " not found.");
        }
        return marque.get();
    }

    public void ajouterMarque(MarqueDto marqueDto) {
        Marque marque = new Marque();
        marque.setNom(marqueDto.getNom());
        marqueRepository.save(marque);
    }

    public void modifierMarque(MarqueDto marqueDto, Long marqueId) {
        Marque marque = verifyMarque(marqueId);
        marque.setNom(marqueDto.getNom());
        marqueRepository.save(marque);
    }

    public void supprimerMarque(Long marqueId) {
        Marque marque = verifyMarque(marqueId);
        marqueRepository.delete(marque);
    }
}
