package main.java.javaserver.cobspec;

import main.java.javaserver.cobspec.handlers.*;
import main.java.javaserver.core.Handler;
import main.java.javaserver.core.Request;
import main.java.javaserver.core.Response;

import java.util.HashMap;

public class Router implements Handler {
    private final HashMap<String, Handler> routes = routes();

    public Response response(Request request) {
        String route = removeParameters(request.route());
        Handler handler = routes.getOrDefault(route, new Unregistered());
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
        routes.put("/text-file.txt", new TextFileTXT());
        routes.put("/parameters", new Parameters());
        routes.put("/partial_content.txt", new PartialContentTXT());
        routes.put("/patch-content.txt", new PatchContentTXT());
        return routes;
    }

    private String removeParameters(String url){
        String[] urlTokens = url.split("[?]");
        return urlTokens[0];
    }
}