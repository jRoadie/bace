package io.bace.http.handler;

import io.bace.http.context.RequestContext;
import io.bace.http.context.ResponseContext;

public interface RequestResponseHandler extends HttpRouteHandler {

    void handle(final RequestContext req, final ResponseContext res);

}
