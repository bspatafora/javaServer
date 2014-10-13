package javaserver.cobspec.handlers;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Status;

public class Logger implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.OK);
        Resources.logsResource.add(request.requestString());
        return response;
    }
}
