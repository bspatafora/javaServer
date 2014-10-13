package javaserver.cobspec.handlers;

import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;
import javaserver.core.helpers.FileSystem;
import javaserver.core.helpers.HTML;
import javaserver.core.helpers.HTTP;

public class Root implements Handler {
    private final Response response = new Response();

    public Response response(Request request) {
        if (request.method().equals(Method.GET)) {
            get();
        } else if (request.method().equals(Method.OPTIONS)) {
            options();
        }
        return response;
    }

    private void get() {
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(body().getBytes());
    }

    private void options() {
        response.setStatus(Status.OK);
        response.addHeader(Header.ALLOW + HTTP.allowedMethods(Root.class));
    }

    private String body() {
        String[] fileNames = FileSystem.directoryContents(Settings.directory);
        StringBuilder fileLinks = new StringBuilder();
        for (String fileName : fileNames) {
            fileLinks.append(HTML.link(fileName, fileName));
            fileLinks.append("</br>");
        }
        return fileLinks.toString();
    }
}
