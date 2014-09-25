package com.bspatafora.core;

import com.bspatafora.cobspec.handlers.Unregistered;
import com.bspatafora.core.constants.Status;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ResponseTest {
    @Test
    public void protocolVersion() throws Exception {
        assertEquals("Protocol version is 'HTTP/1.1'", Status.HTTP11, new Response().protocolVersion());
    }

    @Test
    public void initializeHeadersEmpty() throws Exception {
        assertEquals("Headers is empty on initialization", new ArrayList<String>(), new Response().headers());
    }

    @Test
    public void initializeBodyEmpty() throws Exception {
        byte[] emptyByteArray = new byte[0];
        assertTrue("Body is empty on initialization", Arrays.equals(emptyByteArray, new Response().body()));
    }

    @Test
    public void head() throws Exception {
        String responseHead = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n";
        Request request = new Request();

        Response response = new Unregistered().response(request);
        assertEquals("Generated response head is correct", responseHead, response.head());
    }
}