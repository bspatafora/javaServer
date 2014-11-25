package javaserver.cobspec.handler;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Status;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PartialContentTxtGetTest {
    private static final Request request = new Request();
    static {
        request.setRangeStart(0);
        request.setRangeEnd(4);
    }

    @Test
    public void responseStatus() {
        Response response = new PartialContentTxtGet(request).response();
        assertEquals("Status is '206 Partial Content'", Status.PARTIAL_CONTENT, response.status());
    }

    @Test
    public void responseBodyWhenGET() {
        Response response = new PartialContentTxtGet(request).response();
        assertEquals("Body is the first 4 bytes of CobSpec’s partial_content.txt", "This", new String(response.body()));
    }
}