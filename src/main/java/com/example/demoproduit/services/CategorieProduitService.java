package com.example.demoproduit.services;


import com.example.demoproduit.entities.CategorieProduit;
import com.example.demoproduit.repository.CategorieProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieProduitService {
    public final CategorieProduitRepository categorieProduitRepository ;


    public CategorieProduit ajoutCategorieProduitService(CategorieProduit categorieProduit){
        return categorieProduitRepository.save(categorieProduit);
    }

    public List<CategorieProduit> getall(){
        return categorieProduitRepository.findAll();
    }
}
