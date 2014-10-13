package main.java.javaserver.cobspec.handlers;

import main.java.javaserver.core.Handler;
import main.java.javaserver.core.Request;
import main.java.javaserver.core.Response;
import main.java.javaserver.core.constants.Header;
import main.java.javaserver.core.constants.Method;
import main.java.javaserver.core.constants.Status;
import main.java.javaserver.core.helpers.HTTP;

public class MethodOptions implements Handler {
    private final Response response = new Response();

    public Response response(Request request) {
        switch (request.method()) {
            case Method.GET:
                get();
                break;
            case Method.HEAD:
                head();
                break;
            case Method.POST:
                post();
                break;
            case Method.PUT:
                put();
                break;
            case Method.OPTIONS:
                options();
                break;
        }
        return response;
    }

    private void get() {
        response.setStatus(Status.OK);
    }

    private void head() {
        response.setStatus(Status.OK);
    }

    private void post() {
        response.setStatus(Status.OK);
    }

    private void put() {
        response.setStatus(Status.OK);
    }

    private void options() {
        response.setStatus(Status.OK);
        response.addHeader(Header.ALLOW + HTTP.allowedMethods(MethodOptions.class));
    }
}
