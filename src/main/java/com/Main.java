package com;

import com.handler.TransactionPostHandler;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;

public class Main {

    public static void main(String[] args) {

        HttpHandler rootHandler = new TransactionPostHandler();

        // Just a handler to abstract  others handlers bindings
        RoutingHandler rh = RootRouter.getRoutes(); // Handlers.routing().get("", rootHandler);

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(rh).build();

        server.start();

    }

}