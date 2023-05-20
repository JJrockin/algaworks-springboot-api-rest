package com.jjrockin.spring.dive.domain.service;

import com.jjrockin.spring.dive.domain.model.Client;
import com.jjrockin.spring.dive.domain.model.Delivery;
import com.jjrockin.spring.dive.domain.model.StatusDelivery;
import com.jjrockin.spring.dive.domain.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class RequestDeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private CatalogClientService catalogClientService;
    @Transactional
    public Delivery orderDelivery(Delivery delivery) {
        Client client = catalogClientService.findClientById(delivery.getClient().getId());
        delivery.setClient(client);
        delivery.setStatus(StatusDelivery.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }

}
