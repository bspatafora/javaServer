package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.core.helpers.HTTP;

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
