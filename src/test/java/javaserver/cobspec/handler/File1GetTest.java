package javaserver.cobspec.handler;

import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class File1GetTest {
    @Test
    public void responseContentTypeHeaderWhenGET() {
        Response response = new File1Get().response();
        assertTrue("Content type header is set to 'text/html'", response.headers().contains(Header.CONTENT_TYPE + Header.TEXT_HTML));
    }

    @Test
    public void responseBodyWhenGET() throws IOException {
        File file = new File(Settings.directory + "file1");
        byte[] fileBody = Files.readAllBytes(file.toPath());
        Response response = new File1Get().response();
        assertEquals("Body is CobSpec’s file1", new String(fileBody), new String(response.body()));
    }
}