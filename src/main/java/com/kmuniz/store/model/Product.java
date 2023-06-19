package com.kmuniz.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product(Integer id, String name, Integer quantity, Shipment shipment) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.shipment = shipment;
    }
}