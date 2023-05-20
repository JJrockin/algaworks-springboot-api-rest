package com.jjrockin.spring.dive.api.model.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
public class DeliveryInput {
    @Valid
    @NotNull
    private ClientIdInput client;
    @Valid
    @NotNull
    private RecipientInput recipient;
    @NotNull
    private BigDecimal tax;
}
