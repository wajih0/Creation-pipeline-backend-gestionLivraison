package com.example.demoproduit.controller;

import com.example.demoproduit.entities.CategorieProduit;
import com.example.demoproduit.services.CategorieProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CategorieProduit")
@RequiredArgsConstructor
public class CategorieProduitController {
    public final CategorieProduitService categorieProduitService ;

    @GetMapping("/retriveall")
    public List<CategorieProduit> getall(){
        return categorieProduitService.getall();
    }

    @PostMapping("/addcategorie")
    public CategorieProduit add(CategorieProduit categorieProduit){
        return categorieProduitService.ajoutCategorieProduitService(categorieProduit);
    }

}
