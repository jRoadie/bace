package io.bace.core;

import io.bace.http.HttpServer;
import io.vertx.core.Vertx;

public abstract class BaceApp {

    private HttpServer httpServer;

    public HttpServer httpServer() {
        return httpServer;
    }

    public HttpServer httpServer(HttpServer httpServer) {
        this.httpServer = httpServer;
        return httpServer;
    }

    public static void run(Class<? extends BaceApp> _baceAppClass, String[] args) throws Exception {
        Bace.initialize(_baceAppClass, args);
        Vertx.vertx().deployVerticle(Bace.app().httpServer());
    }

}
