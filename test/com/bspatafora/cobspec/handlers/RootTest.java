package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.constants.Header;
import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import org.junit.Test;

import static org.junit.Assert.*;

public class RootTest {
    private static final Request getRequest = new Request();
    static {
        getRequest.setMethod(Method.GET);
    }

    @Test
    public void responseStatus() throws Exception {
        Response response = new Root().response(getRequest);
        assertEquals("Status is '200 OK'", Status.OK, response.status());
    }

    @Test
    public void responseContentTypeHeader() throws Exception {
        Response response = new Root().response(getRequest);
        assertTrue("Content type header is set to 'text/html'", response.headers().contains(Header.CONTENT_TYPE + Header.TEXT_HTML));
    }

    @Test
    public void responseBody() throws Exception {
        String body = "<a href=\"/.DS_Store\">.DS_Store</a></br>" +
                      "<a href=\"/file1\">file1</a></br>" +
                      "<a href=\"/file2\">file2</a></br>" +
                      "<a href=\"/image.gif\">image.gif</a></br>" +
                      "<a href=\"/image.jpeg\">image.jpeg</a></br>" +
                      "<a href=\"/image.png\">image.png</a></br>" +
                      "<a href=\"/partial_content.txt\">partial_content.txt</a></br>" +
                      "<a href=\"/patch-content.txt\">patch-content.txt</a></br>" +
                      "<a href=\"/text-file.txt\">text-file.txt</a></br>";

        Response response = new Root().response(getRequest);
        assertEquals("Body is links to the CobSpec files", body, new String(response.body()));
    }
}