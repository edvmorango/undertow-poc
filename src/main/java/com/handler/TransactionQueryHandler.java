package com.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;
import java.util.Optional;

public class TransactionQueryHandler implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();


        Optional<Deque<String>> clientName = Optional.ofNullable(queryParameters.get("clientName"));


        exchange.getResponseSender().send("Transaction for queries " + clientName.map(Deque::getFirst).orElse("No QP"));
    }

}
