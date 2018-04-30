package com.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private UUID uid;
    private String clientName;
    private BigDecimal value;
    private LocalDateTime createdAt;


    public Transaction() {
    }

    public Transaction(UUID uid, String clientName, BigDecimal value) {
        this.uid = uid;
        this.clientName = clientName;
        this.value = value;
    }

    public Transaction(UUID uid, String clientName, BigDecimal value, LocalDateTime createdAt) {
        this.uid = uid;
        this.clientName = clientName;
        this.value = value;
        this.createdAt = createdAt;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
