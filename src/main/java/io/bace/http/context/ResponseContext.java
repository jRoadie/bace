package io.bace.http.context;

import io.vertx.ext.web.RoutingContext;

public class ResponseContext {

    private RoutingContext vertxRoutingContext;

    public ResponseContext(RoutingContext routingContext) {
        this.vertxRoutingContext = routingContext;
    }

}
