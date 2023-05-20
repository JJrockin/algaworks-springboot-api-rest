package com.jjrockin.spring.dive.api.controller;

import com.jjrockin.spring.dive.domain.model.Delivery;
import com.jjrockin.spring.dive.domain.repository.DeliveryRepository;
import com.jjrockin.spring.dive.domain.service.RequestDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    @Autowired
    private RequestDeliveryService service;
    @Autowired
    private DeliveryRepository repository;

    @GetMapping
    public List<Delivery> listAllDeliveries(){
        return repository.findAll();
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<Delivery> searchById(@PathVariable Long deliveryId){
        return repository.findById(deliveryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery order(@Valid @RequestBody Delivery delivery) {
        return service.orderDelivery(delivery);
    }

}
