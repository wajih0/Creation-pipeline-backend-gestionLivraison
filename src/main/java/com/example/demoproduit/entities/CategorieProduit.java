package com.example.demoproduit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategorieProduit {

    @Id
    @GeneratedValue
     Long id ;

    String nom ;

    @OneToMany(mappedBy = "categorieProduit")
    List<produit> produitList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<produit> produitList) {
        this.produitList = produitList;
    }
}
