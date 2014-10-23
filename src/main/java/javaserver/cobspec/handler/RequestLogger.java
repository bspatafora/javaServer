package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Status;

public class RequestLogger implements Handler {
    private final Request request;

    public RequestLogger(Request request) {
        this.request = request;
    }

    public Response response() {
        Response response = new Response();
        response.setStatus(Status.OK);
        Resources.logsResource.add(request.requestString());
        return response;
    }
}
