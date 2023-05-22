package com.jjrockin.spring.dive.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @Embedded
    private Recipient recipient;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<EventReport> eventReports = new ArrayList<>();

    private BigDecimal tax;

    @Enumerated(EnumType.STRING)
    private StatusDelivery status;

    private OffsetDateTime orderDate;

    private OffsetDateTime finishDate;

    public EventReport addReport(String description) {
        EventReport eventReport = new EventReport();
        eventReport.setDelivery(this);
        eventReport.setDescription(description);
        eventReport.setRegistryDate(OffsetDateTime.now());

        this.getEventReports().add(eventReport);
        return eventReport;
    }
}
