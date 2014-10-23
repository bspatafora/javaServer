package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;

public class NotFound implements Handler {
    public Response response() {
        Response response = new Response();
        response.setStatus(Status.NOT_FOUND);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody("Not found.".getBytes());
        return response;
    }
}
