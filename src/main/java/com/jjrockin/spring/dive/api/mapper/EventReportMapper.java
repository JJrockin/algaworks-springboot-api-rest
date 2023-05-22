package com.jjrockin.spring.dive.api.mapper;

import com.jjrockin.spring.dive.api.model.EventReportModel;
import com.jjrockin.spring.dive.domain.model.EventReport;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventReportMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EventReportModel toModel(EventReport eventReport){
        return modelMapper.map(eventReport, EventReportModel.class);
    }

    public List<EventReportModel> toCollectionModel(List<EventReport> eventReports){
        return eventReports.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
