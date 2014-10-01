package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.core.*;
import com.bspatafora.core.helpers.FileSystem;
import com.bspatafora.core.helpers.HTML;

public class Root implements Handler {
    private final Response response = new Response();

    public Response response(Request request) {
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
        String[] fileNames = FileSystem.fileNames(Settings.directory);
        StringBuilder fileLinks = new StringBuilder();
        for (String fileName : fileNames) {
            fileLinks.append(HTML.link(fileName, fileName));
            fileLinks.append("</br>");
        }
        return fileLinks.toString();
    }
}
