package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.cobspec.Resources;
import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;

public class Form implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.OK);
        if (request.method().equals(Method.GET)) {
            response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
            response.setBody(Resources.form_resource.getBytes());
        } else if (request.method().equals(Method.POST) || request.method().equals(Method.PUT)) {
            Resources.form_resource = request.body();
        } else if (request.method().equals(Method.DELETE)) {
            Resources.form_resource = "";
        }
        return response;
    }
}
