package io.bace.core;

import io.bace.core.factory.HttpRouterFactory;
import io.bace.http.HttpRouter;
import io.bace.http.HttpServer;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class Bace {

    private static final Integer DEFAULT_HTTP_PORT = 7007;
    private static Bace bace;

    private String[] args;
    private Class<? extends BaceApp> baceAppClass;
    private HttpRouterFactory httpRouterFactory;
    private BaceApp app;

    private Bace() {}

    private void test() {}

    private List<Class> loadBaceAppClasses() {
        BaceAppPackage baceAppPackageAnnotation = baceAppClass.getAnnotation(BaceAppPackage.class);
        if(baceAppPackageAnnotation == null)
            return null;
        String[] baceAppPackages = baceAppPackageAnnotation.value();
        List<Class> classes = new LinkedList<>();
        try {
            for(String pkg : baceAppPackages)
                for(String className : BaceRegistry.listOfClassNames(pkg)) {
                    classes.add(Class.forName(className));
                }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            //TODO:
            // System.out.println();
        } catch(IOException e) {
            //TODO:
            e.printStackTrace();
            System.out.println();
        }

        /* TODO: add other baceapp class registry */
        for(Class clazz : classes) {
            if(HttpRouter.class.isAssignableFrom(clazz))
                BaceRegistry.registerHttpRouters(clazz);
        }

        return classes;
    }

    private void initializeFactories() {
        bace.httpRouterFactory = new HttpRouterFactory();
    }

    private void initializeBaceApp() {
        try {
            app = baceAppClass.newInstance();
            app.httpServer(new HttpServer(DEFAULT_HTTP_PORT)); //TODO: default port should be modifiable
        } catch(InstantiationException e) {
            e.printStackTrace();
            //TODO
        } catch(IllegalAccessException e) {
            e.printStackTrace();
            System.out.printf("");
            //TODO
        }
    }

    public static void initialize(Class<? extends BaceApp> _baceAppClass, String[] args) {
        bace = new Bace();

        bace.args = args;
        bace.baceAppClass = _baceAppClass;

        /* lifecycle */
        bace.initializeFactories();
        bace.loadBaceAppClasses();
        bace.initializeBaceApp();


    }

    public static void testInitialization() {
        try {
            bace.test();
        } catch(NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Bace");
        }
    }

    public static BaceApp app() {
        testInitialization();
        return bace.app;
    }

    public static HttpRouterFactory routerFactory() {
        testInitialization();
        return bace.httpRouterFactory;
    }

}
