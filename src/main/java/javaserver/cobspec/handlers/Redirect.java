package main.java.javaserver.cobspec.handlers;

import main.java.javaserver.core.Handler;
import main.java.javaserver.core.constants.Header;
import main.java.javaserver.core.Request;
import main.java.javaserver.core.Response;
import main.java.javaserver.core.constants.Method;
import main.java.javaserver.core.constants.Status;

public class Redirect implements Handler {
    private final Response response = new Response();

    public Response response(Request request) {
        if (request.method().equals(Method.GET)) {
            get();
        }
        return response;
    }

    private void get() {
        response.setStatus(Status.MOVED_PERMANENTLY);
        response.addHeader(Header.LOCATION + "http://localhost:5000/");
    }
}
