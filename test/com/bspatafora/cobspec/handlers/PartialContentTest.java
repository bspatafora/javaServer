package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartialContentTest {
    private static final Request rangeGETRequest = new Request();
    private static final Request notAllowedRequest = new Request();
    static {
        rangeGETRequest.setMethod(Method.GET);
        notAllowedRequest.setMethod(Method.PUT);

        rangeGETRequest.addHeader(Header.RANGE + "bytes=0-4");
    }

    @Test
    public void responseStatusWhenGET() {
        Response response = new PartialContent().response(rangeGETRequest);

        assertEquals("Status is '206 Partial Content'", Status.PARTIAL_CONTENT, response.status());
    }

    @Test
    public void responseContentTypeHeaderWhenGET() throws Exception {
        Response response = new ImageGIF().response(rangeGETRequest);
        assertTrue("Content type header is set to 'image/gif'", response.headers().contains(Header.CONTENT_TYPE + Header.IMAGE_GIF));
    }

    @Test
    public void responseBodyWhenGET() {
        Response response = new PartialContent().response(rangeGETRequest);

        assertEquals("Body is the first 3 bytes of CobSpec’s partial_content.txt", "This", new String(response.body()));
    }

    @Test
    public void responseStatusWhenNotAllowed() throws Exception {
        Response response = new PartialContent().response(notAllowedRequest);
        Assert.assertEquals("Status is '405 Method Not Allowed'", Status.NOT_ALLOWED, response.status());
    }

    @Test
    public void responseAllowedHeaderWhenNotAllowed() throws Exception {
        Response response = new PartialContent().response(notAllowedRequest);
        assertTrue("Allowed header is set and indicates GET", response.headers().contains(Header.ALLOW + "GET"));
    }
}