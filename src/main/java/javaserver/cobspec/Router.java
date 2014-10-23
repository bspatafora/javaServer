package javaserver.cobspec;

import javaserver.cobspec.handler.*;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Method;

import java.util.ArrayList;
import java.util.HashMap;

public class Router implements javaserver.core.Router {
    private final HashMap<String, HashMap<String, Handler>> handlers = new HashMap<>();
    private Request request;

    public Response response(Request request) {
        this.request = request;
        generateHandlers();
        String route = removeParameters(request.route());
        String method = request.method();
        Handler handler = getHandler(route, method);
        return handler.response();
    }

    public ArrayList<String> methodsAtRoute(String route) {
        generateHandlers();
        HashMap<String, Handler> handlersForRoute = handlers.get(route);
        ArrayList<String> methods = new ArrayList<>();
        for (String method : handlersForRoute.keySet()) {
            methods.add(method);
        }
        return methods;
    }

    private Handler getHandler(String route, String method) {
        Handler handler;
        HashMap<String, Handler> handlersForRoute = handlers.get(route);
        boolean routeIsUnregistered = handlersForRoute == null;
        if (routeIsUnregistered) {
            handler = new NotFound();
        } else {
            handler = handlersForRoute.getOrDefault(method, new MethodNotAllowed());
        }
        return handler;
    }

    private void generateHandlers() {
        add("/", Method.GET, new RootGet());
        add("/", Method.OPTIONS, new Options(request));
        add("/form", Method.GET, new FormGet());
        add("/form", Method.POST, new FormUpdate(request));
        add("/form", Method.PUT, new FormUpdate(request));
        add("/form", Method.DELETE, new FormDelete());
        add("/redirect", Method.GET, new Redirect());
        add("/image.jpeg", Method.GET, new ImageJpegGet());
        add("/image.png", Method.GET, new ImagePngGet());
        add("/image.gif", Method.GET, new ImageGifGet());
        add("/logs", Method.GET, new LogsGet(request));
        add("/log", Method.GET, new RequestLogger(request));
        add("/these", Method.PUT, new RequestLogger(request));
        add("/requests", Method.HEAD, new RequestLogger(request));
        add("/file1", Method.GET, new File1Get());
        add("/method_options", Method.GET, new Ok());
        add("/method_options", Method.HEAD, new Ok());
        add("/method_options", Method.POST, new Ok());
        add("/method_options", Method.PUT, new Ok());
        add("/method_options", Method.OPTIONS, new Options(request));
        add("/parameters", Method.GET, new UrlParameterGet(request));
        add("/partial_content.txt", Method.GET, new PartialContentTxtGet(request));
        add("/patch-content.txt", Method.GET, new PatchContentTxtGet());
        add("/patch-content.txt", Method.PATCH, new PatchContentTxtPatch(request));
        add("/text-file.txt", Method.GET, new Ok());
    }

    private void add(String route, String method, Handler handler) {
        handlers.putIfAbsent(route, new HashMap<>());
        HashMap<String, Handler> handlersForRoute = handlers.get(route);
        handlersForRoute.put(method, handler);
    }

    private String removeParameters(String url){
        String[] urlTokens = url.split("[?]");
        return urlTokens[0];
    }
}