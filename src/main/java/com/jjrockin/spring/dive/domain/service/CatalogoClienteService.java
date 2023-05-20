package com.jjrockin.spring.dive.domain.service;

import com.jjrockin.spring.dive.domain.exception.NegocioException;
import com.jjrockin.spring.dive.domain.model.Cliente;
import com.jjrockin.spring.dive.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogoClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }
        return clienteRepository.save(cliente);
    }
    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
