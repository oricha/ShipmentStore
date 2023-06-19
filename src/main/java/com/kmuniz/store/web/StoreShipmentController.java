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
    Collection<Shipment> shipments(@PathVariable Date date) {
        Collection<Shipment> shipment =   Collections.emptyList();
        if (date != null) {
            shipment = shipmentRepository.findAllByPlannedDeliveryDate(date);
        }
        return shipment;
    }

    @GetMapping("/shipment")
    /**
     *  Filter shipments by date range, verify initialDate and endDate is different from null and paginate the response
     */
    Collection<Shipment> shipments(@RequestParam(required = false) Date iniDate,
                                   @RequestParam(required = false) Date endDate,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) Integer size) {

        if (iniDate != null && endDate != null) {
            var shipment = shipmentRepository.findAllByPlannedDeliveryDateIsBetween(iniDate, endDate);
            return shipment;
        } else {
            if (page != null && size != null) {
                var shipment = shipmentRepository.findAll();
                return shipment;
            } else {
                var shipment = shipmentRepository.findAll();
                return shipment;
            }
        }
    }

    @PutMapping("/shipment")
    ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        log.info("Request to create shipment: {}", shipment);
        Shipment result = shipmentRepository.save(shipment);
        return ResponseEntity.ok().body(result);
    }
}
