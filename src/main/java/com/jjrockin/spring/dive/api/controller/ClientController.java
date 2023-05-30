package com.jjrockin.spring.dive.api.controller;

import com.jjrockin.spring.dive.domain.model.Client;
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
    private CatalogClientService service;
    @GetMapping
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> allClients = service.findAllClients();
        return ResponseEntity.status(HttpStatus.OK).body(allClients);
    }
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> searchById(@PathVariable Long clientId) {
        return service.findClientById(clientId)
                .map(client -> ResponseEntity.status(HttpStatus.OK).body(client))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Client> addNewClient(@Valid @RequestBody Client client) {
        Client savedClient = service.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClientRegistry(@PathVariable Long clientId, @Valid @RequestBody Client client) {
        if (service.findClientById(clientId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        client.setId(clientId);
        client = service.saveClient(client);
        return ResponseEntity.ok(client);
    }
    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClientRegistry(@PathVariable Long clientId){
        if(service.findClientById(clientId).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

}
