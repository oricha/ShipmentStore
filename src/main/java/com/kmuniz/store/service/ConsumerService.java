package com.kmuniz.store.service;

import com.kmuniz.store.model.Shipment;
import com.kmuniz.store.model.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ConsumerService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(Shipment shipment) {
        logger.info("Received Message: " + shipment);
        Shipment saved = shipmentRepository.save(shipment);
        logger.info("persisted " + saved);
    }
}
