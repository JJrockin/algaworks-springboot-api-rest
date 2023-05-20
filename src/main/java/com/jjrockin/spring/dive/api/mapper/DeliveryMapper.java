package com.jjrockin.spring.dive.api.mapper;

import com.jjrockin.spring.dive.api.model.DeliveryModel;
import com.jjrockin.spring.dive.api.model.input.DeliveryInput;
import com.jjrockin.spring.dive.domain.model.Delivery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DeliveryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public DeliveryModel toModel(Delivery delivery){
        return modelMapper.map(delivery, DeliveryModel.class);
    }
    public List<DeliveryModel> toCollectionModel(List<Delivery> deliveries){
        return deliveries.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Delivery toEntity(DeliveryInput deliveryInput){
        return modelMapper.map(deliveryInput, Delivery.class);
    }

}
