package com.example.demoproduit.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StatutCommande getStatus() {
        return status;
    }

    public void setStatus(StatutCommande status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
     StatutCommande status;

    @ManyToOne
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany
    private List<produit> produits;

    @Transient
    private double montantTotal;

    public List<produit> getProduits() {
        return produits;
    }

    public void setProduits(List<produit> produits) {
        this.produits = produits;
    }

}
