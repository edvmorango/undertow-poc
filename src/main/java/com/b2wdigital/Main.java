package com.b2wdigital;

import com.b2wdigital.handler.TransactionHandler;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;

public class Main {

    public static void main(String[] args) {

        HttpHandler rootHandler = new TransactionHandler();


        // Just a handler to abstract  others handlers bindings
        RoutingHandler rh = Handlers.routing().get("", rootHandler);


        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(rh).build();

        server.start();

    }

}