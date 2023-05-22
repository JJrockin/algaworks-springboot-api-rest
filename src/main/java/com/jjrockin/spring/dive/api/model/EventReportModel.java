package com.jjrockin.spring.dive.api.model;

import lombok.Data;

import java.time.OffsetDateTime;
@Data
public class EventReportModel {
    private Long id;
    private String description;
    private OffsetDateTime registryDate;
}
