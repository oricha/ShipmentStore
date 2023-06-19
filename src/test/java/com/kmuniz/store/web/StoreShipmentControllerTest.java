package com.kmuniz.store.web;

import com.kmuniz.store.model.Category;
import com.kmuniz.store.model.Shipment;
import com.kmuniz.store.model.ShipmentRepository;
import com.kmuniz.store.model.Status;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreShipmentControllerTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private StoreShipmentController shipmentController;

    @Test
    public void shipments_ByDate_ReturnsOkResponse() {
        // Mock the repository
        shipmentRepository = mock(ShipmentRepository.class);
        shipmentController = new StoreShipmentController(shipmentRepository);

        // Create a sample date
        Date date = new Date();

        // Create a sample shipment
        Shipment shipment = new Shipment();
        shipment.setId(370123);
        shipment.setStatus(Status.planned);
        shipment.setPlannedDeliveryDate(date);
        shipment.setCategory(Category.truck);
        shipment.setItems(new ArrayList<>());

        // Set up the repository mock to return the sample shipment
        when(shipmentRepository.findAllByPlannedDeliveryDate(date)).thenReturn(Collections.singletonList(shipment));

        // Call the shipments endpoint by date
        ResponseEntity<?> result = shipmentController.shipments(date);

        // Verify the response
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Collections.singletonList(shipment), result.getBody());
        verify(shipmentRepository, times(1)).findAllByPlannedDeliveryDate(date);
    }

    @Test
    public void shipments_ByDateRange_ReturnsOkResponse() {
        // Mock the repository
        shipmentRepository = mock(ShipmentRepository.class);
        shipmentController = new StoreShipmentController(shipmentRepository);

        // Create sample dates
        Date startDate = new Date();
        Date endDate = new Date();

        // Create a sample shipment
        Shipment shipment = new Shipment();
        shipment.setId(1934);
        shipment.setStatus(Status.planned);
        shipment.setPlannedDeliveryDate(startDate);
        shipment.setCategory(Category.truck);
        shipment.setItems(new ArrayList<>());

        // Set up the repository mock to return the sample shipment
        when(shipmentRepository.findAllByPlannedDeliveryDateIsBetween(startDate, endDate)).thenReturn(Collections.singletonList(shipment));

        // Call the shipments endpoint by date range
        ResponseEntity<?> result = shipmentController.shipments(startDate, endDate, null, null);

        // Verify the response
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Collections.singletonList(shipment), result.getBody());
        verify(shipmentRepository, times(1)).findAllByPlannedDeliveryDateIsBetween(startDate, endDate);
    }

    @Test
    public void createShipment_ReturnsOkResponse() {
        // Mock the repository
        shipmentRepository = mock(ShipmentRepository.class);
        shipmentController = new StoreShipmentController(shipmentRepository);

        // Create a sample shipment
        Shipment shipment = new Shipment();
        shipment.setId(12370);
        shipment.setStatus(Status.planned);
        shipment.setPlannedDeliveryDate(new Date());
        shipment.setCategory(Category.truck);
        shipment.setItems(new ArrayList<>());

        // Set up the repository mock to return the sample shipment
        when(shipmentRepository.save(shipment)).thenReturn(shipment);
    }
}