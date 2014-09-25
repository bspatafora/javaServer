package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Status;
import com.bspatafora.core.*;

public class Root implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(body().getBytes());
        return response;
    }

    private String body() {
        return "<a href=\"/file1\">file1</a></br>" +
               "<a href=\"/file2\">file2</a></br>" +
               "<a href=\"/image.gif\">image.gif</a></br>" +
               "<a href=\"/image.jpeg\">image.jpeg</a></br>" +
               "<a href=\"/image.png\">image.png</a></br>" +
               "<a href=\"/text-file.txt\">text-file.txt</a>";
    }
}
