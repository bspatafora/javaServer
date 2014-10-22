package javaserver.cobspec;

import javaserver.cobspec.handler.MethodNotAllowed;
import javaserver.cobspec.handler.NotFound;
import javaserver.cobspec.handlers.*;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Method;

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
        add("/", Method.GET, new Root(request));
        add("/", Method.OPTIONS, new Root(request));
        add("/form", Method.GET, new Form(request));
        add("/form", Method.POST, new Form(request));
        add("/form", Method.PUT, new Form(request));
        add("/form", Method.DELETE, new Form(request));
        add("/redirect", Method.GET, new Redirect(request));
        add("/image.jpeg", Method.GET, new ImageJPEG(request));
        add("/image.png", Method.GET, new ImagePNG(request));
        add("/image.gif", Method.GET, new ImageGIF(request));
        add("/logs", Method.GET, new Logs(request));
        add("/log", Method.GET, new Logger(request));
        add("/these", Method.PUT, new Logger(request));
        add("/requests", Method.HEAD, new Logger(request));
        add("/file1", Method.GET, new File1(request));
        add("/method_options", Method.GET, new MethodOptions(request));
        add("/method_options", Method.HEAD, new MethodOptions(request));
        add("/method_options", Method.POST, new MethodOptions(request));
        add("/method_options", Method.PUT, new MethodOptions(request));
        add("/method_options", Method.OPTIONS, new MethodOptions(request));
        add("/text-file.txt", Method.GET, new TextFileTXT(request));
        add("/parameters", Method.GET, new Parameters(request));
        add("/partial_content.txt", Method.GET, new PartialContentTXT(request));
        add("/patch-content.txt", Method.GET, new PatchContentTXT(request));
        add("/patch-content.txt", Method.PATCH, new PatchContentTXT(request));
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