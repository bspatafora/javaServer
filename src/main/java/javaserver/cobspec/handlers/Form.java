package javaserver.cobspec.handlers;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;

public class Form implements Handler {
    private final Response response = new Response();
    private final Request request;

    public Form(Request request) {
        this.request = request;
    }

    public Response response() {
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
