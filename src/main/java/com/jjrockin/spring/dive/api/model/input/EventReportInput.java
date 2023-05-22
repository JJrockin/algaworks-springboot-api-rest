package com.jjrockin.spring.dive.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class EventReportInput {
    @NotBlank
    private String description;
}
