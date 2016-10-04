package io.bace.http.context;

import io.vertx.ext.web.RoutingContext;

public class HttpResponseContext {

    private RoutingContext vertxRoutingContext;

    public HttpResponseContext(RoutingContext routingContext) {
        this.vertxRoutingContext = routingContext;
    }

}
