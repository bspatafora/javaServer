package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Response;
import javaserver.core.constants.Status;

public class FormDelete implements Handler {
    public Response response() {
        Response response = new Response();
        response.setStatus(Status.OK);
        Resources.formResource = "";
        return response;
    }
}
