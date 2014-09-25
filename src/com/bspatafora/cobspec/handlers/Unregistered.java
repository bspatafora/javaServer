package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Status;

public class Unregistered implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.NOT_FOUND);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody("Not found.".getBytes());
        return response;
    }
}
