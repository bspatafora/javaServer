package com.bspatafora.java_server_tests;

import com.bspatafora.java_server.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    @Test
    public void responseString() throws Exception {
        String responseString = "HTTP/1.1 301 Moved Permanently\r\nLocation: http://localhost:5000/\r\n\r\nMoved.\r\n";
        Request request = new Request();
        request.setMethod(Methods.GET);
        request.setRoute(Routes.REDIRECT);
        request.setProtocolVersion(StatusLine.HTTP11);

        Response response = new ResponseFactory(request).build();
        assertEquals("Generated request string is equal to the original request string", responseString, response.responseString());
    }
}