package com.jjrockin.spring.dive.api.controller;

import com.jjrockin.spring.dive.domain.model.Entrega;
import com.jjrockin.spring.dive.domain.repository.EntregaRepository;
import com.jjrockin.spring.dive.domain.service.SolicitacaoEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
    @Autowired
    private SolicitacaoEntregaService service;
    @Autowired
    private EntregaRepository repository;

    @GetMapping
    public List<Entrega> listar(){
        return repository.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<Entrega> buscar(@PathVariable Long entregaId){
        return repository.findById(entregaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return service.solicitar(entrega);
    }

}
