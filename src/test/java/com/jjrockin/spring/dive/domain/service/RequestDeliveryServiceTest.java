package com.jjrockin.spring.dive.domain.service;

import com.jjrockin.spring.dive.domain.exception.EntityNotFoundException;
import com.jjrockin.spring.dive.domain.model.Client;
import com.jjrockin.spring.dive.domain.model.Delivery;
import com.jjrockin.spring.dive.domain.model.StatusDelivery;
import com.jjrockin.spring.dive.domain.repository.DeliveryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RequestDeliveryServiceTest {

    @InjectMocks
    RequestDeliveryService requestDeliveryService;
    @Mock
    DeliveryRepository deliveryRepository;

    @Captor
    ArgumentCaptor<Delivery> deliveryCaptor;

    @Mock
    CatalogClientService clientService;

    @Test
    @DisplayName("Should save new order successfully")
    void orderDeliverySuccess() {

        Client client = new Client();
        client.setId(1L);
        client.setName("JJ");
        client.setEmail("jeronimo@email.com");
        client.setTelephone("81 98756-8625");

        Delivery delivery = new Delivery();
        delivery.setClient(client);


        Mockito.when(clientService.findClientById(delivery.getClient().getId()))
                .thenReturn(client);

        requestDeliveryService.orderDelivery(delivery);

        Mockito.verify(clientService).findClientById(delivery.getClient().getId());
        Mockito.verify(deliveryRepository).save(deliveryCaptor.capture());

        Delivery deliverySaved = deliveryCaptor.getValue();

        Assertions.assertThat(deliverySaved.getClient()).isNotNull();
        Assertions.assertThat(deliverySaved.getStatus()).isEqualByComparingTo(StatusDelivery.PENDING);
        Assertions.assertThat(deliverySaved.getOrderDate()).isNotNull();

    }

    @Test
    @DisplayName("Should find delivery by Id")
    void findDeliveryById() {

        Long deliveryId = 1L;
        Delivery delivery1 = new Delivery();
        delivery1.setId(deliveryId);

        Mockito.doReturn(Optional.of(delivery1)).when(deliveryRepository).findById(deliveryId);

        Delivery delivery2 = requestDeliveryService.findDeliveryById(deliveryId);

        assertEquals(delivery1, delivery2);

    }

    @Test
    @DisplayName("Throws exception when Id does not match")
    void findDeliveryByIdAndThrows() {

        Mockito.when(deliveryRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        EntityNotFoundException notFoundException =
                assertThrows(EntityNotFoundException.class, () -> requestDeliveryService.findDeliveryById(1L));

        Assertions.assertThat(notFoundException.getMessage()).isEqualTo("Delivery not found");

    }


    @Test
    @DisplayName("Finishes Delivery process")
    void finishDeliveryProcess() {


        Client client = new Client();
        client.setId(1L);
        client.setName("JJ");

        OffsetDateTime now = OffsetDateTime.now();
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setClient(client);
        delivery.setStatus(StatusDelivery.PENDING);
        delivery.setOrderDate(now);

        Mockito.doReturn(Optional.of(delivery)).when(deliveryRepository).findById(delivery.getId());

        requestDeliveryService.finishDeliveryProcess(delivery.getId());

        Mockito.verify(deliveryRepository).save(deliveryCaptor.capture());
        Delivery deliveryFinished = deliveryCaptor.getValue();

        Assertions.assertThat(deliveryFinished.getStatus()).isEqualByComparingTo(StatusDelivery.FINISHED);
        Assertions.assertThat(deliveryFinished.getFinishDate()).isNotNull();

    }
}