package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;
import javaserver.core.helpers.URL;

import java.util.HashMap;

public class UrlParameterGet implements Handler {
    private final Request request;

    public UrlParameterGet(Request request) {
        this.request = request;
    }

    public Response response() {
        Response response = new Response();
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(body().getBytes());
        return response;
    }

    private String body() {
        StringBuilder formattedParameters = new StringBuilder();
        HashMap<String, String> urlParameters = URL.urlParameters(request.route());

        for (String parameterName : urlParameters.keySet()) {
            String decodedParameterValue = URL.percentCode(urlParameters.get(parameterName));

            formattedParameters.append(parameterName);
            formattedParameters.append(" = ");
            formattedParameters.append(decodedParameterValue);
        }
        return formattedParameters.toString();
    }
}
