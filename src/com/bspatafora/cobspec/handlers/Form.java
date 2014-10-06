package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.cobspec.Resources;
import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;

public class Form implements Handler {
    private final Response response = new Response();
    private Request request;

    public Response response(Request request) {
        this.request = request;
        switch (request.method()) {
            case Method.GET:
                get();
                break;
            case Method.POST:
                post();
                break;
            case Method.PUT:
                put();
                break;
            case Method.DELETE:
                delete();
                break;
        }
        return response;
    }

    private void get() {
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(Resources.formResource.getBytes());
    }

    private void post() {
        response.setStatus(Status.OK);
        Resources.formResource = request.body();
    }

    private void put() {
        response.setStatus(Status.OK);
        Resources.formResource = request.body();
    }

    private void delete() {
        response.setStatus(Status.OK);
        Resources.formResource = "";
    }
}
