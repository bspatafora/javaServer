package javaserver.cobspec.handlers;

import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;

public class Unregistered implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.NOT_FOUND);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody("Not found.".getBytes());
        return response;
    }
}
