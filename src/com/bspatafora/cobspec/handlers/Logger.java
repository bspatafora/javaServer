package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Status;
import com.bspatafora.cobspec.Resources;

public class Logger implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.OK);
        Resources.logsResource.add(request.requestString());
        return response;
    }
}
