package com.jjrockin.spring.dive.domain.service;

import com.jjrockin.spring.dive.domain.model.Cliente;
import com.jjrockin.spring.dive.domain.model.Entrega;
import com.jjrockin.spring.dive.domain.model.StatusEntrega;
import com.jjrockin.spring.dive.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class SolicitacaoEntregaService {
    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private CatalogoClienteService catalogoClienteService;
    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }

}
