package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;

public class Redirect implements Handler {
    private final Response response = new Response();

    public Response response(Request request) {
        if (request.method().equals(Method.GET)) {
            get();
        }
        return response;
    }

    private void get() {
        response.setStatus(Status.MOVED_PERMANENTLY);
        response.addHeader(Header.LOCATION + "http://localhost:5000/");
    }
}
