package com.handler;

import com.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.PathTemplateMatch;

import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;

public final class TransactionHandlers {

    public static HttpHandler getHandler() {

        return (HttpServerExchange exchange) -> {
            PathTemplateMatch pathMatch = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY);

            Integer id = Integer.parseInt(pathMatch.getParameters().get("id"));

            exchange.getResponseSender().send("Transaction  get " + id);

        };

    }

    public static HttpHandler postHandler() {

        return (HttpServerExchange exchange) -> {

            if (exchange.isInIoThread()) {
                exchange.dispatch(postHandler());
                return;
            }

            // thread pool java
            ByteBuffer buffer = ByteBuffer.allocate(2048);

            exchange.getRequestChannel().read(buffer);

            byte[] bts = buffer.array();

            ObjectMapper om = new ObjectMapper();

            Transaction transaction = om.readValue(bts, Transaction.class);

            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send(ByteBuffer.wrap(om.writeValueAsBytes(transaction)));

        };

    }

    public static HttpHandler queryHandler() {

        return (HttpServerExchange exchange) -> {

            Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();

            Optional<Deque<String>> clientName = Optional.ofNullable(queryParameters.get("clientName"));

            exchange.getResponseSender().send("Transaction for queries " + clientName.map(Deque::getFirst).orElse("No QP"));

        };

    }


}
