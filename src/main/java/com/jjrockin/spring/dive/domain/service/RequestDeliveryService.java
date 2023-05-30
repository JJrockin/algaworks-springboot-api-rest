package com.jjrockin.spring.dive.domain.service;

import com.jjrockin.spring.dive.domain.exception.EntityNotFoundException;
import com.jjrockin.spring.dive.domain.model.Client;
import com.jjrockin.spring.dive.domain.model.Delivery;
import com.jjrockin.spring.dive.domain.model.StatusDelivery;
import com.jjrockin.spring.dive.domain.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class RequestDeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private CatalogClientService catalogClientService;
    @Transactional
    public Delivery orderDelivery(Delivery delivery) {
        Optional<Client> client = catalogClientService.findClientById(delivery.getClient().getId());
        if(client.isEmpty()){
            throw new EntityNotFoundException("Client not found");
        }
        delivery.setClient(client.get());
        delivery.setStatus(StatusDelivery.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }
    @Transactional
    public Delivery findDeliveryById(Long deliveryId){
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));
    }
    @Transactional
    public void finishDeliveryProcess(Long deliveryId){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));
        delivery.finishDelivery();
        deliveryRepository.save(delivery);
    }

}
