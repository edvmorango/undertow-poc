package com.b2wdigital.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.RequestBufferingHandler;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

public class TransactionPostHandler implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }

        ByteBuffer buffer = ByteBuffer.allocate(2048);

        exchange.getRequestChannel().read(buffer);

        byte[] bts = buffer.array();

        for (byte b : bts) {

            System.out.println((char) b);
        }

        ObjectMapper om = new ObjectMapper();
        ObjectReader or = om.reader();

        JsonNode jsonNode = or.readTree(new ByteArrayInputStream(bts));

        jsonNode.fieldNames().forEachRemaining(System.out::println);

        exchange.getResponseSender().send("Transaction inserted!");
    }


}