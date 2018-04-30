package com.model.enums;

public enum TransactionStatus {

    PENDING,
    APPROVED,
    REFUSED,
    CANCELLED,
    FRAUD;

    public static TransactionStatus of(String name) {
        return name != null ? valueOf(name) : null;
    }

}
