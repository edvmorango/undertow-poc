package com.b2wdigital;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;

public class Main {


    public static void main(String[] args) {

        HttpHandler rootHandler = new HttpHandler() {
            @Override
            public void handleRequest(HttpServerExchange exchange) throws Exception {

                exchange.getResponseSender().send("This is it");
            }
        };


        // Just a handler to abstract  others handlers bindings
        RoutingHandler rh = Handlers.routing().get("", rootHandler);


        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(rh).build();

        server.start();

    }


}
