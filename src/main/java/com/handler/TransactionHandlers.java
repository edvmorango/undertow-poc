package com.handler;

import com.google.inject.Inject;
import com.inject.ApplicationModule;
import com.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.TransactionService;
import com.service.impl.TransactionServiceImpl;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.PathTemplateMatch;

import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class TransactionHandlers {

    private static TransactionService service = ApplicationModule.injector.getInstance(TransactionServiceImpl.class);

    private static ObjectMapper om = new ObjectMapper();


    public static HttpHandler getHandler() {

        return (HttpServerExchange exchange) -> {

            try {

                PathTemplateMatch pathMatch = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY);

                String uid = pathMatch.getParameters().get("id");

                Optional<Transaction> transaction = service.findById(uid);

                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

                if (transaction.isPresent()) {
                    exchange.getResponseSender().send(ByteBuffer.wrap(om.writeValueAsBytes(transaction.get())));
                } else {
                    exchange.getResponseSender().send("{\"message\": \"Couldn't find Transaction.\"}");
                }

            } catch (Exception e) {
                exchange.getResponseSender().send(e.getMessage());
            }


        };

    }

    public static HttpHandler postHandler() {
        return (HttpServerExchange exchange) -> {

            if (exchange.isInIoThread()) {
                exchange.dispatch(postHandler());
                return;
            }

            try {

                ByteBuffer buffer = ByteBuffer.allocate(2048);

                exchange.getRequestChannel().read(buffer);

                byte[] bts = buffer.array();

                Transaction body = om.readValue(bts, Transaction.class);

                Transaction transaction = service.create(body);

                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                exchange.getResponseSender().send(ByteBuffer.wrap(om.writeValueAsBytes(transaction)));
            } catch (Exception e) {
                exchange.getResponseSender().send(e.getMessage());
            }
        };

    }

    public static HttpHandler queryHandler() {

        return (HttpServerExchange exchange) -> {

            Map<String, Deque<String>> queryParameters = exchange.getQueryParameters();

            Optional<Deque<String>> clientName = Optional.ofNullable(queryParameters.get("clientName"));

            List<Transaction> transactions = service.list();

            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send(ByteBuffer.wrap(om.writeValueAsBytes(transactions)));

//            exchange.getResponseSender().send("Transaction for queries " + clientName.map(Deque::getFirst).orElse("No QP"));

        };

    }

}
