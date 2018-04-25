package com.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.math.BigDecimal;
import java.util.UUID;

public class Transaction {

    private UUID id;
    private String clientName;
    private BigDecimal value;

    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
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
}
