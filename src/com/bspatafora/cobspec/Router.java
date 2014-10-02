package com.bspatafora.cobspec;

import com.bspatafora.cobspec.handlers.*;
import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;

import java.util.HashMap;

public class Router implements Handler {
    private final HashMap<String, Handler> routes = routes();

    public Response response(Request request) {
        Handler handler = routes.getOrDefault(request.route(), new Unregistered());
        return handler.response(request);
    }

    private HashMap<String, Handler> routes() {
        HashMap<String, Handler> routes = new HashMap<>();
        routes.put("/", new Root());
        routes.put("/form", new Form());
        routes.put("/redirect", new Redirect());
        routes.put("/image.jpeg", new ImageJPEG());
        routes.put("/image.png", new ImagePNG());
        routes.put("/image.gif", new ImageGIF());
        routes.put("/logs", new Logs());
        routes.put("/log", new Logger());
        routes.put("/these", new Logger());
        routes.put("/requests", new Logger());
        routes.put("/file1", new File1());
        routes.put("/method_options", new MethodOptions());
        return routes;
    }
}
