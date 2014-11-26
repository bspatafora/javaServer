package javaserver.cobspec.handler;

import javaserver.cobspec.Router;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Options implements Handler {
    private final Request request;

    public Options(Request request) {
        this.request = request;
    }

    public Response response() {
        Response response = new Response();
        response.addHeader(Header.ALLOW + allowedMethods());
        return response;
    }

    private String allowedMethods() {
        StringJoiner allowedMethods = new StringJoiner(", ");
        ArrayList<String> methodsAtRoute = new Router().methodsAtRoute(request.route());
        methodsAtRoute.forEach(allowedMethods::add);
        return allowedMethods.toString();
    }
}
