package com.example.demoproduit.controller;

import com.example.demoproduit.DTO.CommandeRequestDTO;
import com.example.demoproduit.DTO.CommandeResponseDTO;
import com.example.demoproduit.entities.Client;
import com.example.demoproduit.entities.Commande;
import com.example.demoproduit.entities.StatutCommande;
import com.example.demoproduit.entities.produit;
import com.example.demoproduit.repository.ClientRepository;
import com.example.demoproduit.repository.ProduitRepository;
import com.example.demoproduit.services.CommandeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commande")
public class CommandeController {
    private final CommandeService commandeService;
    private final ProduitRepository produitRepository;
    private final ClientRepository clientRepository;

    public CommandeController(CommandeService commandeService, ProduitRepository produitRepository,
                              ClientRepository clientRepository) {
        this.commandeService = commandeService;
        this.produitRepository = produitRepository;
        this.clientRepository = clientRepository;
    }
    @GetMapping("/{id}/montant")
    public double getMontantTotal(@PathVariable Long id) {
        return commandeService.calculerMontantTotal(id);
    }

    @PostMapping
    public ResponseEntity<?> ajouterCommande(@RequestBody @Valid CommandeRequestDTO dto) {
        // Récupération des produits
        List<produit> produits = produitRepository.findAllById(dto.getProduitIds());
        if (produits.size() != dto.getProduitIds().size()) {
            return ResponseEntity.badRequest().body("Un ou plusieurs produits sont introuvables.");
        }

        // Récupération du client
        Optional<Client> optionalClient = clientRepository.findById(dto.getClient());
        if (optionalClient.isEmpty()) {
            return ResponseEntity.badRequest().body("Client introuvable.");
        }

        Client client = optionalClient.get();

        // Création de la commande
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setProduits(produits);
        commande.setDate(LocalDate.now());
        commande.setStatus(StatutCommande.EN_COURS); // ou autre valeur par défaut

        // Enregistrement de la commande
        commande = commandeService.enregistrerCommande(commande);

        return ResponseEntity.ok(new CommandeResponseDTO(commande));
    }

    @DeleteMapping("/{commandeId}/produits/{produitId}")
    public ResponseEntity<?> supprimerProduitDeCommande(@PathVariable Long commandeId, @PathVariable Long produitId) {
        try {
            commandeService.supprimerproduitdeCommande(commandeId, produitId);
            return ResponseEntity.ok("Produit supprimé de la commande avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
