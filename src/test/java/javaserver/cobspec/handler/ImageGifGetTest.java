package javaserver.cobspec.handler;

import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ImageGifGetTest {
    @Test
    public void responseContentTypeHeaderWhenGET() {
        Response response = new ImageGifGet().response();
        assertTrue("Content type header is set to 'image/gif'", response.headers().contains(Header.CONTENT_TYPE + Header.IMAGE_GIF));
    }

    @Test
    public void responseBodyWhenGET() throws IOException {
        File file = new File(Settings.directory + "image.gif");
        byte[] image = Files.readAllBytes(file.toPath());
        Response response = new ImageGifGet().response();
        assertEquals("Body is CobSpec’s image.gif", new String(image), new String(response.body()));
    }
}