package javaserver.cobspec;

import javaserver.cobspec.handler.MethodNotAllowed;
import javaserver.cobspec.handler.NotFound;
import javaserver.cobspec.handlers.*;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Method;

import java.util.HashMap;

public class Router implements Handler {
    private final HashMap<String, HashMap<String, Handler>> handlers = new HashMap<>();

    public Router() {
        generateHandlers();
    }

    public Response response(Request request) {
        String route = removeParameters(request.route());
        String method = request.method();
        Handler handler = getHandler(route, method);
        return handler.response(request);
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
        add("/", Method.GET, new Root());
        add("/", Method.OPTIONS, new Root());
        add("/form", Method.GET, new Form());
        add("/form", Method.POST, new Form());
        add("/form", Method.PUT, new Form());
        add("/form", Method.DELETE, new Form());
        add("/redirect", Method.GET, new Redirect());
        add("/image.jpeg", Method.GET, new ImageJPEG());
        add("/image.png", Method.GET, new ImagePNG());
        add("/image.gif", Method.GET, new ImageGIF());
        add("/logs", Method.GET, new Logs());
        add("/log", Method.GET, new Logger());
        add("/these", Method.PUT, new Logger());
        add("/requests", Method.HEAD, new Logger());
        add("/file1", Method.GET, new File1());
        add("/method_options", Method.GET, new MethodOptions());
        add("/method_options", Method.HEAD, new MethodOptions());
        add("/method_options", Method.POST, new MethodOptions());
        add("/method_options", Method.PUT, new MethodOptions());
        add("/method_options", Method.OPTIONS, new MethodOptions());
        add("/text-file.txt", Method.GET, new TextFileTXT());
        add("/parameters", Method.GET, new Parameters());
        add("/partial_content.txt", Method.GET, new PartialContentTXT());
        add("/patch-content.txt", Method.GET, new PatchContentTXT());
        add("/patch-content.txt", Method.PATCH, new PatchContentTXT());
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