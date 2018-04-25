package com.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.PathTemplateMatch;

public class TransactionGetHandler implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        PathTemplateMatch pathMatch = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY);

        Integer id = Integer.parseInt(pathMatch.getParameters().get("id"));


        exchange.getResponseSender().send("Transaction  get " + id);
    }

}
