package com.example.demoproduit.services;

import com.example.demoproduit.entities.Client;
import com.example.demoproduit.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public Optional<Client> findById(Long id) {
        return  clientRepository.findById(id);
    }
}
