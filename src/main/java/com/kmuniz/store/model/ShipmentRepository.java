package com.kmuniz.store.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    @Query(value = "SELECT * FROM shipment s WHERE s.PLANNEDDELIVERYDATE = ?1", nativeQuery = true)
    List<Shipment> findAllByPlannedDeliveryDate(Date date);

    @Query(value = "SELECT * FROM shipment s WHERE s.PLANNEDDELIVERYDATE BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Shipment> findAllByPlannedDeliveryDateIsBetween(
            Date initDate,
            Date endDate);
}
