package com.bspatafora.cobspec;

import com.bspatafora.cobspec.handlers.*;
import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;

import java.util.HashMap;

public class Router implements Handler {
    private static final HashMap<String, Handler> routes;
    static
    {
        routes = new HashMap<>();
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
    }

    public Response response(Request request) {
        Handler notFound = new Unregistered();
        Handler handler = routes.getOrDefault(request.route(), notFound);
        return handler.response(request);
    }
}
