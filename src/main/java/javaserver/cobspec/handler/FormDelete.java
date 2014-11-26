package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Response;

public class FormDelete implements Handler {
    public Response response() {
        Response response = new Response();
        Resources.formResource = "";
        return response;
    }
}
