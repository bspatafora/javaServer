package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.Settings;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Status;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class File1Test {
    private static final Request emptyRequest = new Request();

    @Test
    public void responseStatus() throws Exception {
        Response response = new File1().response(emptyRequest);
        assertEquals("Status is '200 OK'", Status.OK, response.status());
    }

    @Test
    public void responseContentTypeHeader() throws Exception {
        Response response = new File1().response(emptyRequest);
        assertTrue("Content type header is set to 'text/html'", response.headers().contains(Header.CONTENT_TYPE + Header.TEXT_HTML));
    }

    @Test
    public void responseBody() throws Exception {
        File file = new File(Settings.directory + "file1");
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        Response response = new File1().response(emptyRequest);
        assertEquals("Body is CobSpec’s file1 contents", new String(fileBytes), new String(response.body()));
    }
}