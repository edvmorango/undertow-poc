package com.model;

public enum CreditCard {

    NUBANK,
    NEON,
    DIGIO,
    TRIGG;

    public static CreditCard of(String name) {
        return name != null ? valueOf(name) : null;
    }

}
