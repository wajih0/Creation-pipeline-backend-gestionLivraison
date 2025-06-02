package com.example.demoproduit.repository;

import com.example.demoproduit.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // Méthodes de recherche personnalisées si nécessaire

}
