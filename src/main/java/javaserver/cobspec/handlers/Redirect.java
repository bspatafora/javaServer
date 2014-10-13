package javaserver.cobspec.handlers;

import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;

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
