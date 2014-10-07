package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.core.helpers.URL;

import java.util.HashMap;

public class Parameters implements Handler {
    private final Response response = new Response();
    private Request request;

    public Response response(Request request) {
        this.request = request;
        if (request.method().equals(Method.GET)) {
            get();
        }
        return response;
    }

    private void get() {
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(body().getBytes());
    }

    private String body() {
        StringBuilder formattedParameters = new StringBuilder();
        HashMap<String, String> urlParameters = URL.urlParameters(request.route());

        for (String s : urlParameters.keySet()) {
            String parameterName = s;
            String decodedParameterValue = URL.percentCode(urlParameters.get(s));

            formattedParameters.append(parameterName);
            formattedParameters.append(" = ");
            formattedParameters.append(decodedParameterValue);
        }
        return formattedParameters.toString();
    }
}
