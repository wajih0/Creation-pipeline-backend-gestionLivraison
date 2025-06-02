package com.example.demoproduit.services;


import com.example.demoproduit.entities.produit;
import com.example.demoproduit.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProduitService {
    private final ProduitRepository produitRepository ;

    public List<produit> getproduit(){
        return produitRepository.findAll();
    }

    public produit getproduitById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit not found with id " + id));
    }

    public produit ajouterProduit(produit p) {
        return produitRepository.save(p);
    }

    public produit modifierproduit(Long id, produit p) {
        return produitRepository.findById(id)
                .map(existingProduit -> {
                    existingProduit.setNom(p.getNom());
                    existingProduit.setPrix(p.getPrix());

                    return produitRepository.save(existingProduit);
                })
                .orElseThrow(() -> new RuntimeException("Produit not found with id " + id));
    }

    public void supprimerproduit(Long id) {
        produitRepository.deleteById(id);
    }

    public List<produit> findByNomContaining(String nom) {
        return produitRepository.findByNomContaining(nom);
    }


}
