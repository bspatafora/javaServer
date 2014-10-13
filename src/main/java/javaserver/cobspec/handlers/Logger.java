package main.java.javaserver.cobspec.handlers;

import main.java.javaserver.core.Handler;
import main.java.javaserver.core.Request;
import main.java.javaserver.core.Response;
import main.java.javaserver.core.constants.Status;
import main.java.javaserver.cobspec.Resources;

public class Logger implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.OK);
        Resources.logsResource.add(request.requestString());
        return response;
    }
}
