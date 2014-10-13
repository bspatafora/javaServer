package test.java.javaserver.cobspec.handlers;

import main.java.javaserver.cobspec.handlers.File1;
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

public class File1Test {
    private static final Request getRequest = new Request();
    private static final Request notAllowedRequest = new Request();
    static {
        getRequest.setMethod(Method.GET);
        notAllowedRequest.setMethod(Method.PUT);
    }

    @Test
    public void responseStatusWhenGET() throws Exception {
        Response response = new File1().response(getRequest);
        assertEquals("Status is '200 OK'", Status.OK, response.status());
    }

    @Test
    public void responseContentTypeHeaderWhenGET() throws Exception {
        Response response = new File1().response(getRequest);
        assertTrue("Content type header is set to 'text/html'", response.headers().contains(Header.CONTENT_TYPE + Header.TEXT_HTML));
    }

    @Test
    public void responseBodyWhenGET() throws Exception {
        File file = new File(Settings.directory + "file1");
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        Response response = new File1().response(getRequest);
        assertEquals("Body is CobSpec’s file1 contents", new String(fileBytes), new String(response.body()));
    }

    @Test
    public void responseStatusWhenNotAllowed() throws Exception {
        Response response = new File1().response(notAllowedRequest);
        assertEquals("Status is '405 Method Not Allowed'", Status.NOT_ALLOWED, response.status());
    }

    @Test
    public void responseAllowedHeaderWhenNotAllowed() throws Exception {
        Response response = new File1().response(notAllowedRequest);
        assertTrue("Allowed header is set and indicates GET", response.headers().contains(Header.ALLOW + "GET"));
    }
}