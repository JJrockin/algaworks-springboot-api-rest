package com.jjrockin.spring.dive.domain.service;

import com.jjrockin.spring.dive.domain.exception.BusinessRulesException;
import com.jjrockin.spring.dive.domain.model.Client;
import com.jjrockin.spring.dive.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CatalogClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }

    public Optional<Client> findClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }
    public Client saveClient(Client client) {
        boolean emailInUse = clientRepository.findByEmail(client.getEmail())
                .stream()
                .anyMatch(clientExists -> !clientExists.equals(client));
        if (emailInUse) {
            throw new BusinessRulesException("Email already in use");
        }
        return clientRepository.save(client);
    }
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
