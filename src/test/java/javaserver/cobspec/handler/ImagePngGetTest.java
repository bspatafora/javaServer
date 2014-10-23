package javaserver.cobspec.handler;

import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ImagePngGetTest {
    @Test
    public void responseContentTypeHeaderWhenGET() {
        Response response = new ImagePngGet().response();
        assertTrue("Content type header is set to 'image/png'", response.headers().contains(Header.CONTENT_TYPE + Header.IMAGE_PNG));
    }

    @Test
    public void responseBodyWhenGET() throws IOException {
        File file = new File(Settings.directory + "image.png");
        byte[] image = Files.readAllBytes(file.toPath());
        Response response = new ImagePngGet().response();
        assertEquals("Body is CobSpec’s image.png", new String(image), new String(response.body()));
    }
}