package com.jjrockin.spring.dive.api.controller;

import com.jjrockin.spring.dive.api.mapper.DeliveryMapper;
import com.jjrockin.spring.dive.api.model.DeliveryModel;
import com.jjrockin.spring.dive.api.model.input.DeliveryInput;
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
    @Autowired
    private DeliveryMapper deliveryMapper;

    @GetMapping
    public List<DeliveryModel> listAllDeliveries(){
        return deliveryMapper.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryModel> searchById(@PathVariable Long deliveryId){
        return repository.findById(deliveryId)
                .map(delivery -> ResponseEntity.ok(deliveryMapper.toModel(delivery)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryModel order(@Valid @RequestBody DeliveryInput deliveryInput) {
        Delivery newDelivery = deliveryMapper.toEntity(deliveryInput);
        Delivery order = service.orderDelivery(newDelivery);
        return deliveryMapper.toModel(order);
    }

}
