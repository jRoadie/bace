package io.bace.core.factory;

import io.bace.core.BaceRegistry;
import io.bace.http.HttpRouter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpRouterFactory implements BaceFactory {

    private static Map<Class<? extends HttpRouter>, HttpRouter> mapOfHttpRouters = new HashMap<>();

    public HttpRouterFactory() {
        initialize();
    }

    public List<? extends HttpRouter> initialize() {
        BaceRegistry.listOfHttpRouterClasses().forEach(this::register);
        return new LinkedList<>(mapOfHttpRouters.values()); //immutable
    }

    public HttpRouter register(Class<? extends HttpRouter> httpRouterClass) {
        HttpRouter httpRouter = null;
        try {
            httpRouter = httpRouterClass.newInstance();
            httpRouter.initialize();
        } catch(Exception e) {
            e.printStackTrace();
            //TODO:
        }
        mapOfHttpRouters.put(httpRouterClass, httpRouter);
        return httpRouter;
    }

    public HttpRouter instanceOf(Class<? extends HttpRouter> httpRouterClass) {
        return mapOfHttpRouters.get(httpRouterClass);
    }

}
