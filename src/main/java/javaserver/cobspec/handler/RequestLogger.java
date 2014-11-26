package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;

public class RequestLogger implements Handler {
    private final Request request;

    public RequestLogger(Request request) {
        this.request = request;
    }

    public Response response() {
        Response response = new Response();
        Resources.logsResource.add(request.requestString());
        return response;
    }
}
