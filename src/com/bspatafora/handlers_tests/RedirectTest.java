package com.bspatafora.handlers_tests;

import com.bspatafora.handlers.Redirect;
import com.bspatafora.constants.Headers;
import com.bspatafora.java_server.Request;
import com.bspatafora.java_server.Response;
import com.bspatafora.constants.StatusLine;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectTest {
    private static final Request emptyRequest = new Request();

    @Test
    public void responseProtocolVersion() throws Exception {
        Response response = new Redirect().response(emptyRequest);
        assertEquals("Protocol version is 'HTTP/1.1'", StatusLine.HTTP11, response.protocolVersion());
    }

    @Test
    public void responseStatus() throws Exception {
        Response response = new Redirect().response(emptyRequest);
        assertEquals("Status is '302 Moved Permanently'", StatusLine.MOVED_PERMANENTLY, response.status());
    }

    @Test
    public void responseLocationHeader() throws Exception {
        Response response = new Redirect().response(emptyRequest);
        assertTrue("Location header is '/'", response.headers().contains(Headers.LOCATION + "/"));
    }
}