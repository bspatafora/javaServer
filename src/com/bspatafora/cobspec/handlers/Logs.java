package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.cobspec.Resources;

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
            response.setBody(Resources.logs_resource.toString().getBytes());
        } else {
            response.setStatus(Status.UNAUTHORIZED);
            response.setBody("Authentication required".getBytes());
        }
    }

    private Boolean authorized(String credentials) {
        return new String(Base64.getDecoder().decode(credentials)).equals("admin:hunter2");
    }
}
