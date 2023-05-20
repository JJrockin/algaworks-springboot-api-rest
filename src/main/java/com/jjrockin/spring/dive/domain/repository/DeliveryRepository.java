package com.jjrockin.spring.dive.domain.repository;

import com.jjrockin.spring.dive.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
