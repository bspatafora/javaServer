package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.Settings;
import javaserver.core.constants.Header;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class TextFileTXTTest {
    private static final Request getRequest = new Request();
    private static final Request notAllowedRequest = new Request();
    static {
        getRequest.setMethod(Method.GET);
        notAllowedRequest.setMethod(Method.PUT);
    }

    @Test
    public void responseStatusWhenGET() throws Exception {
        Response response = new TextFileTXT(getRequest).response();
        assertEquals("Status is '200 OK'", Status.OK, response.status());
    }

    @Test
    public void responseContentTypeHeaderWhenGET() throws Exception {
        Response response = new TextFileTXT(getRequest).response();
        assertTrue("Content type header is set to 'text/html'", response.headers().contains(Header.CONTENT_TYPE + Header.TEXT_HTML));
    }

    @Test
    public void responseBodyWhenGET() throws Exception {
        File file = new File(Settings.directory + "text-file.txt");
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        Response response = new TextFileTXT(getRequest).response();
        assertEquals("Body is CobSpec’s text-file.txt contents", new String(fileBytes), new String(response.body()));
    }

    @Test
    public void responseStatusWhenNotAllowed() throws Exception {
        Response response = new TextFileTXT(notAllowedRequest).response();
        assertEquals("Status is '405 Method Not Allowed'", Status.NOT_ALLOWED, response.status());
    }

    @Test
    public void responseAllowedHeaderWhenNotAllowed() throws Exception {
        Response response = new TextFileTXT(notAllowedRequest).response();
        assertTrue("Allowed header is set and indicates GET", response.headers().contains(Header.ALLOW + "GET"));
    }
}