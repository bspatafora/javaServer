package com.bspatafora.handlers_tests;

import com.bspatafora.handlers.Root;
import com.bspatafora.constants.Headers;
import com.bspatafora.java_server.Request;
import com.bspatafora.java_server.Response;
import com.bspatafora.constants.StatusLine;
import org.junit.Test;

import static org.junit.Assert.*;

public class RootTest {
    private static final Request emptyRequest = new Request();

    @Test
    public void responseProtocolVersion() throws Exception {
        Response response = new Root().response(emptyRequest);
        assertEquals("Protocol version is 'HTTP/1.1'", StatusLine.HTTP11, response.protocolVersion());
    }

    @Test
    public void responseStatus() throws Exception {
        Response response = new Root().response(emptyRequest);
        assertEquals("Status is '200 OK'", StatusLine.OK, response.status());
    }

    @Test
    public void responseContentTypeHeader() throws Exception {
        Response response = new Root().response(emptyRequest);
        assertTrue("Content type header is set to 'text/html'", response.headers().contains(Headers.CONTENT_TYPE + Headers.TEXT_HTML));
    }

    @Test
    public void responseBody() throws Exception {
        String body = "<a href=\"/file1\">file1</a></br>" +
                "<a href=\"/file2\">file2</a></br>" +
                "<a href=\"/image.gif\">image.gif</a></br>" +
                "<a href=\"/image.jpeg\">image.jpeg</a></br>" +
                "<a href=\"/image.png\">image.png</a></br>" +
                "<a href=\"/text-file.txt\">text-file.txt</a>";

        Response response = new Root().response(emptyRequest);
        assertEquals("Body is links to the CobSpec files", body, response.body());
    }
}