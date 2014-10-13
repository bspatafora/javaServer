package test.java.javaserver.cobspec.handlers;

import main.java.javaserver.cobspec.handlers.ImageJPEG;
import main.java.javaserver.core.Request;
import main.java.javaserver.core.Response;
import main.java.javaserver.core.Settings;
import main.java.javaserver.core.constants.Header;
import main.java.javaserver.core.constants.Method;
import main.java.javaserver.core.constants.Status;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ImageJPEGTest {
    private static final Request getRequest = new Request();
    private static final Request notAllowedRequest = new Request();
    static {
        getRequest.setMethod(Method.GET);
        notAllowedRequest.setMethod(Method.PUT);
    }

    @Test
    public void responseStatusWhenGET() throws Exception {
        Response response = new ImageJPEG().response(getRequest);
        assertEquals("Status is '200 OK'", Status.OK, response.status());
    }

    @Test
    public void responseContentTypeHeaderWhenGET() throws Exception {
        Response response = new ImageJPEG().response(getRequest);
        assertTrue("Content type header is set to 'image/jpeg'", response.headers().contains(Header.CONTENT_TYPE + Header.IMAGE_JPEG));
    }

    @Test
    public void responseBodyWhenGET() throws Exception {
        File file = new File(Settings.directory + "image.jpeg");
        byte[] image = Files.readAllBytes(file.toPath());
        Response response = new ImageJPEG().response(getRequest);
        assertEquals("Body is CobSpec’s image.jpeg", new String(image), new String(response.body()));
    }

    @Test
    public void responseStatusWhenNotAllowed() throws Exception {
        Response response = new ImageJPEG().response(notAllowedRequest);
        assertEquals("Status is '405 Method Not Allowed'", Status.NOT_ALLOWED, response.status());
    }

    @Test
    public void responseAllowedHeaderWhenNotAllowed() throws Exception {
        Response response = new ImageJPEG().response(notAllowedRequest);
        assertTrue("Allowed header is set and indicates GET", response.headers().contains(Header.ALLOW + "GET"));
    }
}