package io.bace.http.handler;

import io.bace.http.context.HttpRequestContext;
import io.bace.http.context.HttpResponseContext;

public interface HttpRequestResponseHandler extends HttpRouteHandler {

    void handle(HttpRequestContext req, HttpResponseContext res);

}
