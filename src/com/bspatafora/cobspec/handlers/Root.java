package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Status;
import com.bspatafora.core.*;
import com.bspatafora.helpers.FileSystem;
import com.bspatafora.helpers.HTML;

public class Root implements Handler {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(body().getBytes());
        return response;
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
