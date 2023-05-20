package com.jjrockin.spring.dive.api.controller;

import com.jjrockin.spring.dive.domain.model.Client;
import com.jjrockin.spring.dive.domain.repository.ClientRepository;
import com.jjrockin.spring.dive.domain.service.CatalogClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CatalogClientService service;
    @GetMapping
    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> searchById(@PathVariable Long clientId) {
        return clientRepository.findById(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client addNewClient(@Valid @RequestBody Client client) {
        return service.saveClient(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClientRegistry(@PathVariable Long clientId, @Valid @RequestBody Client client) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(clientId);
        client = service.saveClient(client);
        return ResponseEntity.ok(client);
    }
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClientRegistry(@PathVariable Long clientId){
        if(!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }
        service.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

}
