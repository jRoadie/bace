package io.bace.core.factory;

import io.bace.core.BaceRegistry;
import io.bace.http.HttpRestRouter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpRouterFactory implements BaceFactory {

    private static Map<Class<? extends HttpRestRouter>, HttpRestRouter> mapOfHttpRouters = new HashMap<>();

    public HttpRouterFactory() {
        initialize();
    }

    public List<? extends HttpRestRouter> initialize() {
        BaceRegistry.listOfHttpRouterClasses().forEach(this::register);
        return new LinkedList<>(mapOfHttpRouters.values()); //immutable
    }

    public HttpRestRouter register(Class<? extends HttpRestRouter> httpRouterClass) {
        HttpRestRouter httpRestRouter = null;
        try {
            httpRestRouter = httpRouterClass.newInstance();
            httpRestRouter.initialize();
        } catch(Exception e) {
            e.printStackTrace();
            //TODO:
        }
        mapOfHttpRouters.put(httpRouterClass, httpRestRouter);
        return httpRestRouter;
    }

    public HttpRestRouter instanceOf(Class<? extends HttpRestRouter> httpRouterClass) {
        return mapOfHttpRouters.get(httpRouterClass);
    }

}
