package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;

public class Redirect implements Handler {
    public Response response() {
        Response response = new Response();
        response.setStatus(Status.MOVED_PERMANENTLY);
        response.addHeader(Header.LOCATION + "http://localhost:5000/");
        return response;
    }
}
