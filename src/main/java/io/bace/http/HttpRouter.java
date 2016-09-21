package io.bace.http;

import io.bace.http.handler.HttpReqResHandler;
import io.bace.http.handler.HttpRouteHandler;
import io.bace.http.handler.HttpRoutingContextHandler;
import io.vertx.core.http.HttpMethod;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public class HttpRouter<R> {

    private String mountPoint;
    private List<HttpRoute> listOfHttpRoutes = new LinkedList<>();

    public void initialize() {
        for(HttpRoute httpRoute : listOfHttpRoutes) {
            httpRoute.register();
        }
    }

    public R subRouteOf(String mountPoint) {
        this.mountPoint = mountPoint;
        return (R)this;
    }

    public R get(String path, HttpReqResHandler handler) {
        registerHttpRoute(path, HttpMethod.GET, handler);
        return (R)this;
    }

    public R post(String path, HttpRoutingContextHandler handler) {
        registerHttpRoute(path, HttpMethod.POST, handler);
        return (R)this;
    }

    private R route(String path, HttpMethod httpMethod, HttpRouteHandler handler) {
        registerHttpRoute(path, httpMethod, handler);
        return (R)this;
    }

    public void registerHttpRoute(String path, HttpMethod httpMethod, HttpRouteHandler handler) {
        listOfHttpRoutes.add(new HttpRoute(path, httpMethod, handler));
    }

}
