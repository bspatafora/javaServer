package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartialContentTXTTest {
    private static final Request rangeGETRequest = new Request();
    private static final Request notAllowedRequest = new Request();
    static {
        rangeGETRequest.setMethod(Method.GET);
        notAllowedRequest.setMethod(Method.PUT);

        rangeGETRequest.addHeader(Header.RANGE + "bytes=0-4");
    }

    @Test
    public void responseStatusWhenGET() {
        Response response = new PartialContentTXT().response(rangeGETRequest);

        assertEquals("Status is '206 Partial Content'", Status.PARTIAL_CONTENT, response.status());
    }

    @Test
    public void responseContentTypeHeaderWhenGET() throws Exception {
        Response response = new ImageGIF().response(rangeGETRequest);
        assertTrue("Content type header is set to 'image/gif'", response.headers().contains(Header.CONTENT_TYPE + Header.IMAGE_GIF));
    }

    @Test
    public void responseBodyWhenGET() {
        Response response = new PartialContentTXT().response(rangeGETRequest);

        assertEquals("Body is the first 3 bytes of CobSpec’s partial_content.txt", "This", new String(response.body()));
    }

    @Test
    public void responseStatusWhenNotAllowed() throws Exception {
        Response response = new PartialContentTXT().response(notAllowedRequest);
        Assert.assertEquals("Status is '405 Method Not Allowed'", Status.NOT_ALLOWED, response.status());
    }

    @Test
    public void responseAllowedHeaderWhenNotAllowed() throws Exception {
        Response response = new PartialContentTXT().response(notAllowedRequest);
        assertTrue("Allowed header is set and indicates GET", response.headers().contains(Header.ALLOW + "GET"));
    }
}