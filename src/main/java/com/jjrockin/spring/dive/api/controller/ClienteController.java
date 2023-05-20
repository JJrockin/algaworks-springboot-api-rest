package com.jjrockin.spring.dive.api.controller;

import com.jjrockin.spring.dive.domain.model.Cliente;
import com.jjrockin.spring.dive.domain.repository.ClienteRepository;
import com.jjrockin.spring.dive.domain.service.CatalogoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CatalogoClienteService service;
    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return service.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        cliente = service.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        service.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }

}
