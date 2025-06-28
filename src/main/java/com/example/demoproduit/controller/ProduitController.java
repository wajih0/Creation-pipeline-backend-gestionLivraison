package com.example.demoproduit.controller;

import com.example.demoproduit.entities.produit;
import com.example.demoproduit.services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/produit")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProduitController {
    private  final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
    }


    @GetMapping("retriveall")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<produit> getproduit() {
        return produitService.getproduit();
    }

    @GetMapping("/retrive/{id}")
    public produit getproduitById(@PathVariable Long id) {
        return produitService.getproduitById(id);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public produit ajouterProduit(@RequestBody produit produit) {
        return produitService.ajouterProduit(produit);
    }

    @PutMapping("/modifier/{id}")
    public produit modifierproduit(@PathVariable Long id, @RequestBody produit produit) {

        return produitService.modifierproduit(id, produit);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerproduit(@PathVariable Long id) {
        produitService.supprimerproduit(id);
    }

    @GetMapping("/search")
    public List<produit> findByNomContaining(@RequestParam String nom) {
        return produitService.findByNomContaining(nom);
    }
}
