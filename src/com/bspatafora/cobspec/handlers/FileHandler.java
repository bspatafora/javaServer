package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.Settings;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

abstract class FileHandler {
    String contentType;
    String fileName;
    private final Response response = new Response();

    protected abstract void setContentType();
    protected abstract void setFileName();

    public Response response(Request request) {
        setContentType();
        setFileName();

        if (request.method().equals(Method.GET)) {
            get();
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
}
