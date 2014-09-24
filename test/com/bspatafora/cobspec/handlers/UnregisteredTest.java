package com.bspatafora.cobspec.handlers;

import com.bspatafora.javaserver.Request;
import com.bspatafora.javaserver.Response;
import com.bspatafora.javaserver.constants.StatusLine;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnregisteredTest {
    private static final Request emptyRequest = new Request();

    @Test
    public void responseStatus() throws Exception {
        Response response = new Unregistered().response(emptyRequest);
        assertEquals("Status is '404 Not Found'", StatusLine.NOT_FOUND, response.status());
    }

    @Test
    public void responseBody() throws Exception {
        String body = "Not found.";

        Response response = new Unregistered().response(emptyRequest);
        assertEquals("Body is 'Not found.'", body, response.body());
    }
}