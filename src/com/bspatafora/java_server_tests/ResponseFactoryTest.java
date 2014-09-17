package com.bspatafora.java_server_tests;

import com.bspatafora.java_server.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseFactoryTest {
    @Test
    public void setProtocolVersionHTTP11() throws Exception {
        Request request = new Request();
        request.setMethod(Methods.GET);
        request.setRoute(Routes.ROOT);
        request.setProtocolVersion(StatusLine.HTTP11);

        Response response = new ResponseFactory(request).build();
        assertEquals("Built response has its protocol version set to HTTP/1.1", StatusLine.HTTP11, response.protocolVersion());
    }

    @Test
    public void setStatusOK() throws Exception {
        Request request = new Request();
        request.setMethod(Methods.GET);
        request.setRoute(Routes.ROOT);

        Response response = new ResponseFactory(request).build();
        assertEquals("Built response has its status set to '200 OK'", StatusLine.OK, response.status());
    }

    @Test
    public void setStatusNotFound() throws Exception {
        Request request = new Request();
        request.setMethod(Methods.GET);
        request.setRoute("/unregistered");

        Response response = new ResponseFactory(request).build();
        assertEquals("Built response has its status set to '404 Not Found'", StatusLine.NOT_FOUND, response.status());
    }

    @Test
    public void setBodyHelloWorld() throws Exception {
        Request request = new Request();
        request.setMethod(Methods.GET);
        request.setRoute(Routes.ROOT);

        Response response = new ResponseFactory(request).build();
        assertEquals("Built response has its body set to 'Hello, world!'", "Hello, world!", response.body());
    }

    @Test
    public void setBodyFormResource() throws Exception {
        Resources.form_resource = "Test data";
        Request request = new Request();
        request.setMethod(Methods.GET);
        request.setRoute(Routes.FORM);

        Response response = new ResponseFactory(request).build();
        assertEquals("Built response has its body set to the value of the /form resource", Resources.form_resource, response.body());
    }

    @Test
    public void setBodyNotFound() throws Exception {
        Request request = new Request();
        request.setMethod(Methods.GET);
        request.setRoute("/unregistered");

        Response response = new ResponseFactory(request).build();
        assertEquals("Built response has its body set to 'Not found.'", "Not found.", response.body());
    }
}