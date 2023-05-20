package com.jjrockin.spring.dive.api.controller;

import com.jjrockin.spring.dive.domain.model.Entrega;
import com.jjrockin.spring.dive.domain.service.SolicitacaoEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
    @Autowired
    private SolicitacaoEntregaService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody Entrega entrega) {
        return service.solicitar(entrega);
    }

}
