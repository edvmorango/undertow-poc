package com.model;

import com.model.enums.CreditCard;
import com.model.enums.TransactionStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class Transaction {

    private UUID uid;
    private String clientName;
    private BigDecimal value;
    private LocalDateTime createdAt;
    private CreditCard creditCard;
    private TransactionStatus transactionStatus;
    private Set<String> historic;

    public Transaction() {
    }

    public Transaction(UUID uid, String clientName, BigDecimal value) {
        this.uid = uid;
        this.clientName = clientName;
        this.value = value;
    }

    public Transaction(UUID uid, String clientName, BigDecimal value, LocalDateTime createdAt, CreditCard creditCard, TransactionStatus transactionStatus, Set<String> historic) {
        this.uid = uid;
        this.clientName = clientName;
        this.value = value;
        this.createdAt = createdAt;
        this.creditCard = creditCard;
        this.transactionStatus = transactionStatus;
        this.historic = historic;
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

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Set<String> getHistoric() {
        return historic;
    }

    public void setHistoric(Set<String> historic) {
        this.historic = historic;
    }
}
