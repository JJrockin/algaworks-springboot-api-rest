package com.jjrockin.spring.dive.domain.service;

import com.jjrockin.spring.dive.domain.model.Delivery;
import com.jjrockin.spring.dive.domain.model.EventReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventReportService {
    @Autowired
    private RequestDeliveryService deliveryService;
    @Transactional
    public EventReport createReport(Long deliveryId, String description){
        Delivery delivery = deliveryService.findDeliveryById(deliveryId);
        return delivery.addReport(description);
    }
    @Transactional
    public Delivery getDeliveryById(Long deliveryId){
        return deliveryService.findDeliveryById(deliveryId);
    }
}
