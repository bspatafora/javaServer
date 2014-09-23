package com.bspatafora.java_server_tests;

import com.bspatafora.constants.Methods;
import com.bspatafora.java_server.Request;
import com.bspatafora.java_server.RequestFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RequestFactoryTest {
    private static final String getRoot = "GET / HTTP/1.1\r\n";
    private static final String postForm = "POST /form HTTP/1.1\r\nContent-Length: 10\r\n\r\ndata=cosby\r\n";

    @Test
    public void setMethodGET() throws Exception {
        String requestString = getRoot;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        Assert.assertEquals("Built request has its method set to GET", Methods.GET, request.method());
    }

    @Test
    public void setMethodPOST() throws Exception {
        String requestString = postForm;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its method set to POST", Methods.POST, request.method());
    }

    @Test
    public void setRouteRoot() throws Exception {
        String requestString = getRoot;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its route set to /", "/", request.route());
    }

    @Test
    public void setRouteSlashForm() throws Exception {
        String requestString = postForm;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its route to /form", "/form", request.route());
    }

    @Test
    public void setProtocolVersionHTTP11() throws Exception {
        String requestString = getRoot;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its protocol version set to HTTP/1.1", "HTTP/1.1", request.protocolVersion());
    }

    @Test
    public void initializeContentLength0() throws Exception {
        String requestString = getRoot;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its content length initialized to 0", 0, request.contentLength());
    }

    @Test
    public void setContentLength10() throws Exception {
        String requestString = postForm;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its content length set to 10", 10, request.contentLength());
    }

    @Test
    public void initializeBodyNull() throws Exception {
        String requestString = getRoot;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its body initialized to null", null, request.body());
    }

    @Test
    public void setBodyDataEqualsCosby() throws Exception {
        String requestString = postForm;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its body set to data=cosby", "data=cosby", request.body());
    }

    @Test
    public void initializeHeaders() throws Exception {
        String requestString = getRoot;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its headers initialized to an empty array list", new ArrayList<>(), request.headers());
    }

    @Test
    public void setHeaderContentLength() throws Exception {
        String requestString = postForm;
        InputStream inputStream = new ByteArrayInputStream(requestString.getBytes(StandardCharsets.UTF_8));
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        List<String> headers = new ArrayList<>();
        headers.add("Content-Length: 10");
        Request request = new RequestFactory(in).build();
        assertEquals("Built request has its headers set to its Content-Length header", headers, request.headers());
    }
}
