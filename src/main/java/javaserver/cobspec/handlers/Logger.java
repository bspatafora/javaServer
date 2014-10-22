package javaserver.cobspec.handlers;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Status;

public class Logger implements Handler {
    private final Request request;

    public Logger(Request request) {
        this.request = request;
    }

    public Response response() {
        Response response = new Response();
        response.setStatus(Status.OK);
        Resources.logsResource.add(request.requestString());
        return response;
    }
}
