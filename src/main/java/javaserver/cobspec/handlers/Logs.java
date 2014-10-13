package javaserver.cobspec.handlers;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;

import java.util.Base64;

public class Logs implements Handler {
    private final Response response = new Response();
    private Request request;

    public Response response(Request request) {
        this.request = request;
        if (request.method().equals(Method.GET)) {
            get();
        }
        return response;
    }

    private void get() {
        if (authorized(request.credentials())) {
            response.setStatus(Status.OK);
            response.setBody(Resources.logsResource.toString().getBytes());
        } else {
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody("Authentication required".getBytes());
        }
    }

    private Boolean authorized(String credentials) {
        return new String(Base64.getDecoder().decode(credentials)).equals("admin:hunter2");
    }
}
