package io.bace.http;

import io.bace.http.handler.HttpParamHandler;
import io.bace.http.handler.HttpRequestResponseHandler;
import io.bace.http.handler.HttpRouteHandler;
import io.bace.http.handler.HttpRoutingContextHandler;
import io.vertx.core.http.HttpMethod;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class HttpRouter<R> {

    private String mountPoint;
    private List<HttpRoute> listOfHttpRoutes = new LinkedList<>();

    public void initialize() {
        for(HttpRoute httpRoute : listOfHttpRoutes) {
            httpRoute.register();
        }
    }

    protected R subRouteOf(String mountPoint) {
        this.mountPoint = mountPoint;
        return (R)this;
    }

    protected R get(String path, HttpRoutingContextHandler handler) {
        registerHttpRoute(path, HttpMethod.GET, handler);
        return (R)this;
    }

    protected R get(String path, HttpRequestResponseHandler handler) {
        registerHttpRoute(path, HttpMethod.GET, handler);
        return (R)this;
    }

    protected R get(String path, HttpParamHandler handler) {
        registerHttpRoute(path, HttpMethod.GET, handler);
        return (R)this;
    }

    protected R post(String path, HttpRouteHandler handler, Object... objects) {
        registerHttpRoute(path, HttpMethod.POST, handler);
        return (R)this;
    }

    protected R route(String path, HttpMethod httpMethod, HttpRouteHandler handler) {
        registerHttpRoute(path, httpMethod, handler);
        return (R)this;
    }

    private void registerHttpRoute(String path, HttpMethod httpMethod, HttpRouteHandler handler) {
        listOfHttpRoutes.add(
                new HttpRoute(
                        mountPoint == null ? path : mountPoint + path,
                        httpMethod,
                        handler
                )
        );
    }

}
