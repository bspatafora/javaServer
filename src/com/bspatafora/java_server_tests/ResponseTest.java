package com.bspatafora.java_server_tests;

import com.bspatafora.handlers.Unregistered;
import com.bspatafora.java_server.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    @Test
    public void responseString() throws Exception {
        String responseString = "HTTP/1.1 404 Not Found\r\n\r\nNot found.\r\n";
        Request request = new Request();

        Response response = new Unregistered().response(request);
        assertEquals("Generated request string is equal to the original request string", responseString, response.responseString());
    }
}