package javaserver.cobspec.handler;

import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ImageJpegGetTest {
    @Test
    public void responseContentTypeHeaderWhenGET() {
        Response response = new ImageJpegGet().response();
        assertTrue("Content type header is set to 'image/jpeg'", response.headers().contains(Header.CONTENT_TYPE + Header.IMAGE_JPEG));
    }

    @Test
    public void responseBodyWhenGET() throws IOException {
        File file = new File(Settings.directory + "image.jpeg");
        byte[] image = Files.readAllBytes(file.toPath());
        Response response = new ImageJpegGet().response();
        assertEquals("Body is CobSpec’s image.jpeg", new String(image), new String(response.body()));
    }
}