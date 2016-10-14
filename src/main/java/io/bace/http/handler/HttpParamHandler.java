package io.bace.http.handler;

import io.bace.http.context.HttpParamContext;

public interface HttpParamHandler<R> extends HttpRouteHandler {

    R handle(HttpParamContext param);

}
