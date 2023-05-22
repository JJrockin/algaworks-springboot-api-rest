package com.jjrockin.spring.dive.api.controller;

import com.jjrockin.spring.dive.api.mapper.EventReportMapper;
import com.jjrockin.spring.dive.api.model.EventReportModel;
import com.jjrockin.spring.dive.api.model.input.EventReportInput;
import com.jjrockin.spring.dive.domain.model.Delivery;
import com.jjrockin.spring.dive.domain.model.EventReport;
import com.jjrockin.spring.dive.domain.service.EventReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("deliveries/{deliveryId}/events")
public class EventReportController {
    @Autowired
    private EventReportService eventReportService;
    @Autowired
    private EventReportMapper eventMapper;

    @GetMapping
    public List<EventReportModel> listOfEvents(@PathVariable Long deliveryId){
        Delivery delivery = eventReportService.getDeliveryById(deliveryId);
        List<EventReport> eventReports = delivery.getEventReports();
        return eventMapper.toCollectionModel(eventReports);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventReportModel registryEvent(@PathVariable Long deliveryId,
                                          @Valid @RequestBody EventReportInput reportInput){
        EventReport registryEvent = eventReportService.createReport(deliveryId, reportInput.getDescription());
        return eventMapper.toModel(registryEvent);
    }
}
