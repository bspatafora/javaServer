package javaserver.cobspec.handler;

import javaserver.core.Handler;
import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;
import javaserver.core.helpers.FileSystem;

import java.io.File;

abstract class FileGet implements Handler {
    private String contentType;
    private String fileName;

    void setContentType(String contentType) {
        this.contentType = contentType;
    }

    void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Response response() {
        File file = new File(Settings.directory + fileName);
        Response response = new Response();
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + contentType);
        response.setBody(FileSystem.read(file));
        return response;
    }
}
