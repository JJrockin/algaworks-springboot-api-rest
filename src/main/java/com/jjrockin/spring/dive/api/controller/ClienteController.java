package com.jjrockin.spring.dive.api.controller;

import com.jjrockin.spring.dive.domain.model.Cliente;
import com.jjrockin.spring.dive.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }
}
