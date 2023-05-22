package com.jjrockin.spring.dive.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class EventReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne
    private Delivery delivery;
    private String description;
    private OffsetDateTime registryDate;
}
