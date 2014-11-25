package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;
import javaserver.core.helpers.FileSystem;

import java.io.File;

public class PartialContentTxtGet implements Handler {
    private final Request request;

    public PartialContentTxtGet(Request request) {
        this.request = request;
    }

    public Response response() {
        File file = new File(Settings.directory + "partial_content.txt");
        Response response = new Response();
        response.setStatus(Status.PARTIAL_CONTENT);
        response.addHeader(Header.CONTENT_TYPE + Header.TEXT_HTML);
        response.setBody(FileSystem.readPartial(file, request.rangeStart(), request.rangeEnd()));
        return response;
    }
}
