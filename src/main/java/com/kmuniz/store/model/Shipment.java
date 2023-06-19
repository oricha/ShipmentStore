package com.kmuniz.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Status status;
    @Temporal(TemporalType.DATE)
    @Column(name = "PLANNEDDELIVERYDATE")
    private Date plannedDeliveryDate;
    @NotNull
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipment")
    private List<Product> items;

    public Shipment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public Date getPlannedDeliveryDate() {
        return plannedDeliveryDate;
    }

    public void setPlannedDeliveryDate(Date plannedDeliveryDate) {
        this.plannedDeliveryDate = plannedDeliveryDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Shipments{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", plannedDeliveryDate=" + plannedDeliveryDate +
                ", category=" + category +
                ", items=" + items +
                '}';
    }

    public Shipment(Integer id, Status status, Date plannedDeliveryDate, Category category, List<Product> items) {
        this.id = id;
        this.status = status;
        this.plannedDeliveryDate = plannedDeliveryDate;
        this.category = category;
        this.items = items;
    }
}
