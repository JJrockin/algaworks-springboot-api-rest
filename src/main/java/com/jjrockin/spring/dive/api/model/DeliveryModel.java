package com.jjrockin.spring.dive.api.model;

import com.jjrockin.spring.dive.domain.model.StatusDelivery;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Data
public class DeliveryModel {

    private Long id;
    private String clientName;
    private RecipientModel recipient;
    private BigDecimal tax;
    private StatusDelivery status;
    private OffsetDateTime orderDate;
    private OffsetDateTime finishDate;

}
