package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.Settings;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.core.helpers.FileSystem;
import com.bspatafora.core.helpers.HTTP;

import java.io.File;

abstract class FileHandler {
    private String contentType;
    private String fileName;
    private final Response response = new Response();
    private Request request = new Request();

    void setContentType(String contentType) {
        this.contentType = contentType;
    }

    void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Response response(Request request) {
        this.request = request;
        if (request.method().equals(Method.GET)) {
            get();
        } else {
            not_allowed();
        }
        return response;
    }

    void get() {
        response.addHeader(Header.CONTENT_TYPE + contentType);
        File file = new File(Settings.directory + fileName);
        if (requestIsPartial()) {
            response.setStatus(Status.PARTIAL_CONTENT);
            response.setBody(FileSystem.readPartial(file, startByteIndex(), endByteIndex()));
        } else {
            response.setStatus(Status.OK);
            response.setBody(FileSystem.read(file));
        }
    }

    void not_allowed() {
        response.setStatus(Status.NOT_ALLOWED);
        response.addHeader(Header.ALLOW + HTTP.allowedMethods(FileHandler.class));
    }

    private Boolean requestIsPartial() {
        return !rangeHeader().isEmpty();
    }

    private int startByteIndex() {
        return rangeHeaderCharacterAt(13);
    }

    private int endByteIndex() {
        return rangeHeaderCharacterAt(15);
    }

    private int rangeHeaderCharacterAt(int index) {
        return Character.getNumericValue(rangeHeader().charAt(index));
    }

    private String rangeHeader() {
        String rangeHeader = "";
        for (String header : request.headers()) {
            if (header.contains(Header.RANGE)) {
                rangeHeader = header;
            }
        }
        return rangeHeader;
    }
}
