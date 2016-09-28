package io.bace.http;

public interface HttpRouteHandler {

    void handle(HttpRequestContext req, HttpResponseContext res);

}
