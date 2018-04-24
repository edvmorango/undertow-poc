package com.b2wdigital;

import com.b2wdigital.handler.TransactionGetHandler;
import com.b2wdigital.handler.TransactionHandlers;
import com.b2wdigital.handler.TransactionPostHandler;
import com.b2wdigital.handler.TransactionQueryHandler;
import io.undertow.server.RoutingHandler;

public final class RootRouter {

    private final static RoutingHandler root = initRoutes();


    private static RoutingHandler initRoutes() {
        return new RoutingHandler()
                .get("/transaction/{id}", TransactionHandlers.getHandler())
                .get("/transaction", TransactionHandlers.queryHandler())
                .post("/transaction", TransactionHandlers.postHandler());
    }


    public static RoutingHandler getRoutes() {
        return root;
    }


}







