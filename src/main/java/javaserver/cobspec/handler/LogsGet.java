package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Status;

import java.util.Base64;

public class LogsGet implements Handler {
    private final Response response = new Response();
    private final Request request;

    public LogsGet(Request request) {
        this.request = request;
    }

    public Response response() {
        if (validCredentials()) {
            authorized();
        } else {
            unauthorized();
        }
        return response;
    }

    private void unauthorized() {
        response.setStatus(Status.UNAUTHORIZED);
        response.setBody("Authentication required".getBytes());
    }

    private void authorized() {
        response.setStatus(Status.OK);
        response.setBody(Resources.logsResource.toString().getBytes());
    }

    private Boolean validCredentials() {
        String requestCredentials = new String(Base64.getDecoder().decode(request.credentials()));
        return requestCredentials.equals("admin:hunter2");
    }
}
