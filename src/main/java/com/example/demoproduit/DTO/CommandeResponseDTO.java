package com.example.demoproduit.DTO;

import com.example.demoproduit.entities.Client;
import com.example.demoproduit.entities.Commande;
import com.example.demoproduit.entities.produit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommandeResponseDTO {
    private Long id;
    private Client client;
    private List<String> produits;

    // Constructeur pour transformer l'entité en DTO
    public CommandeResponseDTO(Commande commande) {
        this.id = commande.getId();
        this.client = commande.getClient();
        this.produits = commande.getProduits().stream()
                .map(produit::getNom)
                .toList();
    }
}
