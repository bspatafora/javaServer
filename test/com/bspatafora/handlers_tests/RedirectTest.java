package com.bspatafora.handlers_tests;

import com.bspatafora.handlers.Redirect;
import com.bspatafora.javaserver.constants.Headers;
import com.bspatafora.javaserver.Request;
import com.bspatafora.javaserver.Response;
import com.bspatafora.javaserver.constants.StatusLine;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectTest {
    private static final Request emptyRequest = new Request();

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