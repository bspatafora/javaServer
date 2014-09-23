package com.bspatafora.handlers_tests;

import com.bspatafora.handlers.Unregistered;
import com.bspatafora.java_server.Request;
import com.bspatafora.java_server.Response;
import com.bspatafora.constants.StatusLine;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnregisteredTest {
    private static final Request emptyRequest = new Request();

    @Test
    public void responseProtocolVersion() throws Exception {
        Response response = new Unregistered().response(emptyRequest);
        assertEquals("Protocol version is 'HTTP/1.1'", StatusLine.HTTP11, response.protocolVersion());
    }

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