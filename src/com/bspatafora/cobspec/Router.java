package com.bspatafora.cobspec;

import com.bspatafora.cobspec.handlers.Form;
import com.bspatafora.cobspec.handlers.Redirect;
import com.bspatafora.cobspec.handlers.Root;
import com.bspatafora.cobspec.handlers.Unregistered;
import com.bspatafora.javaserver.Handler;
import com.bspatafora.javaserver.Request;
import com.bspatafora.javaserver.Response;

import java.util.HashMap;

public class Router implements Handler {
    private static final HashMap<String, Object> routes;
    static
    {
        routes = new HashMap<>();
        routes.put("/", new Root());
        routes.put("/form", new Form());
        routes.put("/redirect", new Redirect());
    }

    public Response response(Request request) {
        Object handler = routes.getOrDefault(request.route(), new Unregistered());
        return ((Handler)handler).response(request);
    }
}
