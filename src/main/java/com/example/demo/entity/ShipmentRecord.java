package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shipment_records")
public class ShipmentRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String shipmentCode;

    private String origin;
    private String destination;
    private String status;

    public ShipmentRecord() {}

    public ShipmentRecord(String shipmentCode, String origin, String destination, String status) {
        this.shipmentCode = shipmentCode;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    @PrePersist
    public void prePersist() {
        if (status == null || status.isBlank()) status = "IN_TRANSIT";
    }

    // getters and setters...
}