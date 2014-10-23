package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Response;
import javaserver.core.constants.Status;

public class MethodNotAllowed implements Handler {
    public Response response() {
        Response response = new Response();
        response.setStatus(Status.NOT_ALLOWED);
        return response;
    }
}
