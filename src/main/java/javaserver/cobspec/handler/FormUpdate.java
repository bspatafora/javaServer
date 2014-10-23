package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Status;

public class FormUpdate implements Handler {
    private final Request request;

    public FormUpdate(Request request) {
        this.request = request;
    }

    public Response response() {
        Response response = new Response();
        response.setStatus(Status.OK);
        Resources.formResource = request.body();
        return response;
    }
}
