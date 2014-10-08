package com.bspatafora.cobspec.handlers;

import com.bspatafora.core.Request;
import com.bspatafora.core.Response;
import com.bspatafora.core.constants.Header;
import com.bspatafora.core.constants.Method;
import com.bspatafora.core.constants.Status;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PartialContentTest {
    private static final Request rangeGETRequest = new Request();
    static {
        rangeGETRequest.setMethod(Method.GET);
        rangeGETRequest.addHeader(Header.RANGE + "bytes=0-4");
    }

    @Test
    public void responseStatus() {
        Response response = new PartialContent().response(rangeGETRequest);

        assertEquals("Status is '206 Partial Content'", Status.PARTIAL_CONTENT, response.status());
    }

    @Test
    public void responseBody() {
        Response response = new PartialContent().response(rangeGETRequest);

        assertEquals("Body is the first 3 bytes of CobSpec’s partial_content.txt", "This", new String(response.body()));
    }
}