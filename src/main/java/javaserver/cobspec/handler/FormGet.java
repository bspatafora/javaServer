package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import javaserver.core.Handler;
import javaserver.core.Response;
import javaserver.core.constants.Header;

public class FormGet implements Handler {
    public Response response() {
        Response response = new Response();
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(Resources.formResource.getBytes());
        return response;
    }
}
