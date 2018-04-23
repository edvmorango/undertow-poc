package com.b2wdigital;

import com.b2wdigital.handler.TransactionHandler;
import io.undertow.server.RoutingHandler;

public final class RootRouter {

    private final static RoutingHandler root = initRoutes();

    private static RoutingHandler initRoutes() {
        return new RoutingHandler().get("/transaction", new TransactionHandler());
    }

    public static RoutingHandler getRoutes() {
        return root;
    }


}







