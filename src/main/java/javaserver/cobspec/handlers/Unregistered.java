package main.java.javaserver.cobspec.handlers;

import main.java.javaserver.core.Handler;
import main.java.javaserver.core.Request;
import main.java.javaserver.core.Response;
import main.java.javaserver.core.constants.Header;
import main.java.javaserver.core.constants.Status;

public class Unregistered implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.NOT_FOUND);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody("Not found.".getBytes());
        return response;
    }
}
