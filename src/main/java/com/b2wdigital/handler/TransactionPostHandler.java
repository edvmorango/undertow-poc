package com.b2wdigital.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.RequestBufferingHandler;

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


        exchange.getResponseSender().send("Transaction inserted!");
    }


}