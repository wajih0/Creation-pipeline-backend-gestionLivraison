package com.example.demoproduit.controller;

import com.example.demoproduit.entities.Client;
import com.example.demoproduit.entities.Commande;
import com.example.demoproduit.services.ClientService;
import com.example.demoproduit.services.CommandeService;
import com.example.demoproduit.services.FactureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/factures")
public class FactureController {

    private final FactureService factureService;
    private final ClientService clientService;
    private final CommandeService commandeService;

    public FactureController(FactureService factureService ,ClientService clientService,  CommandeService commandeService) {
        this.factureService = factureService;
        this.commandeService = commandeService;
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<byte[]> telechargerFacture(@PathVariable Long id) throws IOException {
        // Récupérer le client et la commande à partir de l'ID
        Client client =  clientService.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        Commande commande = commandeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        byte[] pdf = factureService.genererFacturePdf(client, commande);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "facture_" + id + ".pdf");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}

