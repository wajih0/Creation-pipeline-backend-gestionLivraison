package com.example.demoproduit.services;

import com.example.demoproduit.entities.Commande;
import com.example.demoproduit.entities.produit;
import com.example.demoproduit.repository.CommandeRepository;
import com.example.demoproduit.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final ProduitRepository produitRepository;

    public  CommandeService(CommandeRepository commandeRepository , ProduitRepository produitRepository) {
        this.commandeRepository = commandeRepository;
        this.produitRepository = produitRepository;
    }

    public double calculerMontantTotal(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        return commande.getProduits().stream()
                .mapToDouble(produit::getPrix)
                .sum();
    }

    public Commande enregistrerCommande(Commande commande) {
        // Implémentation de l'enregistrement
        return commandeRepository.save(commande);
    }
    public Optional<Commande> findById(Long id) {
        return commandeRepository.findById(id);
    }

    public void supprimerproduitdeCommande(Long commandeid , Long produitid) {
        Commande commande = commandeRepository.findById(commandeid)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        produit produit = produitRepository.findById(produitid)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        commande.getProduits().remove(produit);
        commandeRepository.save(commande);


    }

}
