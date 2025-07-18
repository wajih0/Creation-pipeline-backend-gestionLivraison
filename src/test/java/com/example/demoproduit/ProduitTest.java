package com.example.demoproduit;
import com.example.demoproduit.entities.CategorieProduit;
import com.example.demoproduit.entities.produit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProduitTest {
    @Test
    void testProduitGettersSetters() {
        produit produit = new produit();
        produit.setId(1L);
        produit.setNom("Produit Test");
        produit.setPrix(99.99);

        assertEquals(1L, produit.getId());
        assertEquals("Produit Test", produit.getNom());
        assertEquals(99.99, produit.getPrix(), 0.01);
    }

    @Test
    void testProduitAllArgsConstructor() {
        CategorieProduit categorie = new CategorieProduit();
        produit produit = new produit(2L, "Produit Catégorie", 150.0, categorie);

        assertEquals(2L, produit.getId());
        assertEquals("Produit Catégorie", produit.getNom());
        assertEquals(150.0, produit.getPrix(), 0.01);
        assertEquals(categorie, produit.getCategorieProduit());
    }
}
