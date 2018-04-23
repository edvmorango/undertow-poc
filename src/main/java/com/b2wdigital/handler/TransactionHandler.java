package com.b2wdigital.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;


public class TransactionHandler implements HttpHandler {


    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        exchange.getResponseSender().send("Payment");
    }


}