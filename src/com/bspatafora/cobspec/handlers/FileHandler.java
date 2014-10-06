package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.Settings;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.core.helpers.HTTP;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

abstract class FileHandler {
    String contentType;
    String fileName;
    private final Response response = new Response();

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Response response(Request request) {
        if (request.method().equals(Method.GET)) {
            get();
        } else {
            not_allowed();
        }
        return response;
    }

    void get() {
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + contentType);
        File file = new File(Settings.directory + fileName);
        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            response.setBody(fileBytes);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    void not_allowed() {
        response.setStatus(Status.NOT_ALLOWED);
        response.addHeader(Header.ALLOW + HTTP.allowedMethods(FileHandler.class));
    }
}
