package com.example.demoproduit.DTO;

import com.example.demoproduit.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class CommandeRequestDTO {


    @NotNull
    private Long client;

    @NotEmpty
    private List<Long> produitIds;

    public @NotEmpty List<Long> getProduitIds() {
        return produitIds;
    }

    public @NotNull Long getClient() {
        return client;
    }

    public void setClient(@NotNull Long client) {
        this.client = client;
    }

    public void setProduitIds(@NotEmpty List<Long> produitIds) {
        this.produitIds = produitIds;
    }

}
