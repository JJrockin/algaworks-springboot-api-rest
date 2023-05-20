package com.jjrockin.spring.dive.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientIdInput {
    @NotNull
    private Long id;
}
