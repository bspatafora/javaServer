package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Handler;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.Settings;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import com.bspatafora.core.helpers.FileSystem;
import com.bspatafora.core.helpers.HTTP;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;

abstract class FileHandler implements Handler {
    private String contentType;
    private String fileName;
    private String filePath;
    private final Response response = new Response();
    private Request request;

    void setContentType(String contentType) {
        this.contentType = contentType;
    }

    void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Response response(Request request) {
        this.request = request;
        this.filePath = Settings.directory + fileName;
        if (request.method().equals(Method.GET)) {
            get();
        } else if (request.method().equals(Method.PATCH)) {
            patch();
        } else {
            not_allowed();
        }
        return response;
    }

    void get() {
        response.addHeader(Header.CONTENT_TYPE + contentType);

        File file = new File(filePath);
        Boolean partialGet = request.hasHeader(Header.RANGE);
        if (partialGet) {
            partialGet(file);
        } else {
            fullGet(file);
        }
    }

    private void fullGet(File file) {
        response.setStatus(Status.OK);
        response.setBody(FileSystem.read(file));
    }

    private void partialGet(File file) {
        response.setStatus(Status.PARTIAL_CONTENT);
        response.setBody(FileSystem.readPartial(file, startByteIndex(), endByteIndex()));
    }

    void patch() {
        response.setStatus(Status.NO_CONTENT);
        if (resourceInExpectedState()) {
            patchResource();
        }
    }

    void not_allowed() {
        response.setStatus(Status.NOT_ALLOWED);
        response.addHeader(Header.ALLOW + HTTP.allowedMethods(FileHandler.class));
    }

    private int startByteIndex() {
        return rangeHeaderCharacterAt(13);
    }

    private int endByteIndex() {
        return rangeHeaderCharacterAt(15);
    }

    private int rangeHeaderCharacterAt(int index) {
        String rangeHeader = request.getHeader(Header.RANGE);
        return Character.getNumericValue(rangeHeader.charAt(index));
    }

    private Boolean resourceInExpectedState() {
        Boolean matchingSHA1s = false;
        try (FileInputStream resourceContents = new FileInputStream(filePath)
        ) {
            String resourceSHA1 = DigestUtils.sha1Hex(resourceContents);
            matchingSHA1s = resourceSHA1.equals(requestSHA1());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return matchingSHA1s;
    }

    private void patchResource() {
        int updateStartByteIndex = 0;
        int updateEndByteIndex = request.contentLength();
        byte[] update = request.body().getBytes();

        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")
        ) {
            file.write(update, updateStartByteIndex, updateEndByteIndex);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private String requestSHA1() {
        return request.getHeader(Header.IF_MATCH).substring(10);
    }
}
