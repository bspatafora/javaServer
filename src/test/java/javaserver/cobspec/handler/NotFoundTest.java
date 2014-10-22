package javaserver.cobspec.handler;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class NotFoundTest {
    @Test
    public void responseStatus() {
        Response response = new NotFound().response();
        assertEquals("Status is '404 Not Found'", Status.NOT_FOUND, response.status());
    }

    @Test
    public void responseContentTypeHeader() {
        Response response = new NotFound().response();
        assertTrue("Content type header is set to 'text/html'", response.headers().contains(Header.CONTENT_TYPE + Header.TEXT_HTML));
    }

    @Test
    public void responseBody() {
        Response response = new NotFound().response();
        assertEquals("Body is 'Not found.'", "Not found.", new String(response.body()));
    }
}