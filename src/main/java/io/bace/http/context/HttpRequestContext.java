package io.bace.http.context;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

public class HttpRequestContext {

    private RoutingContext vertxRoutingContext;

    public HttpRequestContext(RoutingContext rctx) {
        this.vertxRoutingContext = rctx;
    }

    public <T> T param(String key) {
        return null;
    }

    public HttpServerRequest vertxRequest() {
        return vertxRoutingContext.request();
    }

}
