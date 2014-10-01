package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.Settings;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

abstract class FileHandler {
    String contentType;
    String fileName;

    protected abstract void setContentType();
    protected abstract void setFileName();

    public Response response(Request request) {
        setContentType();
        setFileName();
        Response response = new Response();
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + contentType);
        File file = new File(Settings.directory + fileName);
        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            response.setBody(fileBytes);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }
}
