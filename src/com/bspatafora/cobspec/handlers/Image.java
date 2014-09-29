package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.Settings;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class Image {
    protected String contentType;
    protected String fileName;

    public abstract void setContentType();
    public abstract void setFileName();

    public Response response(Request request) {
        setContentType();
        setFileName();
        Response response = new Response();
        response.setStatus(Status.OK);
        response.addHeader(Header.CONTENT_TYPE + contentType);
        File file = new File(Settings.directory + fileName);
        try {
            byte[] image = Files.readAllBytes(file.toPath());
            response.setBody(image);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return response;
    }
}
