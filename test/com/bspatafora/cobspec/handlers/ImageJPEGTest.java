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

public class ImageJPEGTest {
    private static final Request emptyRequest = new Request();

    @Test
    public void responseStatus() throws Exception {
        Response response = new ImageJPEG().response(emptyRequest);
        assertEquals("Status is '200 OK'", Status.OK, response.status());
    }

    @Test
    public void responseContentTypeHeader() throws Exception {
        Response response = new ImageJPEG().response(emptyRequest);
        assertTrue("Content type header is set to 'image/jpeg'", response.headers().contains(Header.CONTENT_TYPE + Header.IMAGE_JPEG));
    }

    @Test
    public void responseBody() throws Exception {
        File file = new File(Settings.directory + "image.jpeg");
        byte[] image = Files.readAllBytes(file.toPath());
        Response response = new ImageJPEG().response(emptyRequest);
        assertEquals("Body is CobSpec’s image.jpeg", new String(image), new String(response.body()));
    }
}