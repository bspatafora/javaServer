package com.bspatafora.handlers;

import com.bspatafora.constants.Methods;
import com.bspatafora.constants.StatusLine;
import com.bspatafora.helpers.Resources;
import com.bspatafora.java_server.*;

public class Form implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setProtocolVersion(StatusLine.HTTP11);
        response.setStatus(StatusLine.OK);
        if (request.method().equals(Methods.GET)) {
            response.setBody(Resources.form_resource);
        } else if (request.method().equals(Methods.POST) || request.method().equals(Methods.PUT)) {
            Resources.form_resource = request.body();
        } else if (request.method().equals(Methods.DELETE)) {
            Resources.form_resource = null;
        }
        return response;
    }
}
