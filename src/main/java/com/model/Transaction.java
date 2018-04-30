package com.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private UUID uid;
    private String clientName;
    private BigDecimal value;
    private LocalDateTime createdAt;
    private CreditCard creditCard;

    public Transaction() {
    }

    public Transaction(UUID uid, String clientName, BigDecimal value) {
        this.uid = uid;
        this.clientName = clientName;
        this.value = value;
    }

    public Transaction(UUID uid, String clientName, BigDecimal value, LocalDateTime createdAt, CreditCard creditCard) {
        this.uid = uid;
        this.clientName = clientName;
        this.value = value;
        this.createdAt = createdAt;
        this.creditCard = creditCard;
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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
