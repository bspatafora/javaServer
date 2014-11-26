package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import javaserver.core.helpers.FileSystem;
import javaserver.core.helpers.HTML;

public class RootGet implements Handler {
    public Response response() {
        Response response = new Response();
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(body().getBytes());
        return response;
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
