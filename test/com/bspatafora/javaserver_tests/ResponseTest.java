package com.bspatafora.javaserver_tests;

import com.bspatafora.handlers.Unregistered;
import com.bspatafora.javaserver.*;
import com.bspatafora.javaserver.constants.StatusLine;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {
    @Test
    public void protocolVersion() throws Exception {
        assertEquals("Protocol version is 'HTTP/1.1'", StatusLine.HTTP11, new Response().protocolVersion());
    }

    @Test
    public void string() throws Exception {
        String responseString = "HTTP/1.1 404 Not Found\r\n\r\nNot found.\r\n";
        Request request = new Request();

        Response response = new Unregistered().response(request);
        assertEquals("Generated response string is equal to the original response string", responseString, response.responseString());
    }
}