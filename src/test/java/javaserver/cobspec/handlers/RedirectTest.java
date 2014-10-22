package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Header;
import javaserver.core.constants.Method;
import javaserver.core.constants.Status;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectTest {
    private static final Request getRequest = new Request();
    static {
        getRequest.setMethod(Method.GET);
    }

    @Test
    public void responseStatus() throws Exception {
        Response response = new Redirect(getRequest).response();
        assertEquals("Status is '302 Moved Permanently'", Status.MOVED_PERMANENTLY, response.status());
    }

    @Test
    public void responseLocationHeader() throws Exception {
        Response response = new Redirect(getRequest).response();
        assertTrue("Location header is 'http://localhost:5000/'", response.headers().contains(Header.LOCATION + "http://localhost:5000/"));
    }
}