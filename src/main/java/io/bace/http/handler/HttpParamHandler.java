package io.bace.http.handler;

import io.bace.http.context.HttpParamContext;
import io.bace.http.context.HttpRequestContext;
import io.bace.http.context.HttpResponseContext;

public interface HttpParamHandler extends HttpRouteHandler {

    Object handle(HttpParamContext param);

}
