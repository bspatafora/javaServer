package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnregisteredTest {
    private static final Request emptyRequest = new Request();

    @Test
    public void responseStatus() throws Exception {
        Response response = new Unregistered().response(emptyRequest);
        assertEquals("Status is '404 Not Found'", Status.NOT_FOUND, response.status());
    }

    @Test
    public void responseContentTypeHeader() throws Exception {
        Response response = new Unregistered().response(emptyRequest);
        assertTrue("Content type header is set to 'text/html'", response.headers().contains(Header.CONTENT_TYPE + Header.TEXT_HTML));
    }

    @Test
    public void responseBody() throws Exception {
        String body = "Not found.";

        Response response = new Unregistered().response(emptyRequest);
        assertEquals("Body is 'Not found.'", body, new String(response.body()));
    }
}