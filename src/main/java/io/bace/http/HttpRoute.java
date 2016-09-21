package io.bace.http;

import io.bace.core.Bace;
import io.bace.http.handler.HttpReqResHandler;
import io.bace.http.handler.HttpRouteHandler;
import io.vertx.core.Handler;
import io.vertx.core.http.*;
import io.vertx.ext.web.Router;

public class HttpRoute {

    private String path;
    private HttpMethod httpMethod;
    private HttpRouteHandler httpRouteHandler;

    public HttpRoute(String path, HttpMethod httpMethod, HttpRouteHandler httpRouteHandler) {
        this.path = path;
        this.httpMethod = httpMethod;
        this.httpRouteHandler = httpRouteHandler;
    }

    public void register() {
        Bace.app().httpServer().router().route(path).method(httpMethod).handler(rctx -> {
            if(httpRouteHandler instanceof HttpReqResHandler)
                ((HttpReqResHandler)httpRouteHandler).handle(rctx.request(), rctx.response());
        });
    }

    @Override
    public boolean equals(Object o) {
        //TODO: ensure proper implementation
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        HttpRoute httpRoute = (HttpRoute)o;

        if(!path.equals(httpRoute.path)) return false;
        return httpMethod == httpRoute.httpMethod;

    }

    @Override
    public int hashCode() {
        //TODO: ensure proper implementation
        int result = path.hashCode();
        result = 31 * result + httpMethod.hashCode();
        return result;
    }
}
