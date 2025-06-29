package com.example.demoproduit;

import com.example.demoproduit.controller.ProduitController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demoproduit.entities.produit;
import com.example.demoproduit.services.ProduitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
class DemoproduitApplicationTests {

	@Test
	void contextLoads() {
	}
//
//	private MockMvc mockMvc;
//	private ProduitService produitService;
//	private ObjectMapper objectMapper;
//
//	@BeforeEach
//	void setUp() {
//		produitService = mock(ProduitService.class);
//		ProduitController produitController = new ProduitController(produitService);
//		mockMvc = MockMvcBuilders.standaloneSetup(produitController).build();
//		objectMapper = new ObjectMapper();
//	}
//
//	@Test
//	void testGetAllProduits() throws Exception {
//		produit p1 = new produit(1L, "Produit1", 10.0);
//		produit p2 = new produit(2L, "Produit2", 20.0);
//		List<produit> produits = Arrays.asList(p1, p2);
//
//		when(produitService.getproduit()).thenReturn(produits);
//
//		mockMvc.perform(get("/produit/retriveall"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.length()").value(produits.size()));
//	}
//
//	@Test
//	void testGetProduitById() throws Exception {
//		produit p = new produit(1L, "ProduitTest", 15.0);
//		when(produitService.getproduitById(1L)).thenReturn(p);
//
//		mockMvc.perform(get("/produit/retrive/1"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.nom").value("ProduitTest"));
//	}
//
//	@Test
//	void testAjouterProduit() throws Exception {
//		produit input = new produit(null, "NouveauProduit", 30.0);
//		produit saved = new produit(1L, "NouveauProduit", 30.0);
//
//		when(produitService.ajouterProduit(any(produit.class))).thenReturn(saved);
//
//		mockMvc.perform(post("/produit")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(input)))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.id").value(1));
//	}
//
//	@Test
//	void testModifierProduit() throws Exception {
//		produit updated = new produit(1L, "ProduitModifié", 40.0);
//
//		when(produitService.modifierproduit(eq(1L), any(produit.class))).thenReturn(updated);
//
//		mockMvc.perform(put("/produit/modifier/1")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(updated)))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.nom").value("ProduitModifié"));
//	}
//
//	@Test
//	void testSupprimerProduit() throws Exception {
//		doNothing().when(produitService).supprimerproduit(1L);
//
//		mockMvc.perform(delete("/produit/supprimer/1"))
//				.andExpect(status().isOk());
//
//		verify(produitService, times(1)).supprimerproduit(1L);
//	}
//
//	@Test
//	void testFindByNomContaining() throws Exception {
//		produit p = new produit(1L, "ProduitCherché", 50.0);
//		when(produitService.findByNomContaining("Cherché")).thenReturn(List.of(p));
//
//		mockMvc.perform(get("/produit/search").param("nom", "Cherché"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.length()").value(1))
//				.andExpect(jsonPath("$[0].nom").value("ProduitCherché"));
//	}

}
