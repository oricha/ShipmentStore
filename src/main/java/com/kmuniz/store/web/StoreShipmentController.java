package com.kmuniz.store.web;


import com.kmuniz.store.model.Shipment;
import com.kmuniz.store.model.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class StoreShipmentController {

    private final Logger log = LoggerFactory.getLogger(StoreShipmentController.class);
    private final ShipmentRepository shipmentRepository;


    public StoreShipmentController(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }
    @GetMapping("/shipment/{date}")
    ResponseEntity<?> shipments(@PathVariable Date date) {
        Collection<Shipment> shipment =   Collections.emptyList();
        try{
            if (date != null) {
                shipment = shipmentRepository.findAllByPlannedDeliveryDate(date);
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(shipment);
    }

    @GetMapping("/shipment")
    ResponseEntity<?> shipments(@RequestParam(required = false) Date iniDate,
                                   @RequestParam(required = false) Date endDate,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) Integer size) {

        if (iniDate != null && endDate != null) {
            var shipment = shipmentRepository.findAllByPlannedDeliveryDateIsBetween(iniDate, endDate);
            return ResponseEntity.ok().body(shipment);
        } else {
           return  ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/shipment")
    ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        log.info("Request to create shipment: {}", shipment);
        Shipment result = shipmentRepository.save(shipment);
        return ResponseEntity.ok().body(result);
    }
}
