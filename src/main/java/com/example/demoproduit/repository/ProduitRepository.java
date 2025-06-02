package com.example.demoproduit.repository;

import com.example.demoproduit.entities.produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<produit,Long> {
    List<produit> findByNomContaining(String nom);
}
