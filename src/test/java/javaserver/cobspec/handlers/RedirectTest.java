package test.java.javaserver.cobspec.handlers;

import main.java.javaserver.cobspec.handlers.Redirect;
import main.java.javaserver.core.constants.Header;
import main.java.javaserver.core.Request;
import main.java.javaserver.core.Response;
import main.java.javaserver.core.constants.Method;
import main.java.javaserver.core.constants.Status;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectTest {
    private static final Request getRequest = new Request();
    static {
        getRequest.setMethod(Method.GET);
    }

    @Test
    public void responseStatus() throws Exception {
        Response response = new Redirect().response(getRequest);
        assertEquals("Status is '302 Moved Permanently'", Status.MOVED_PERMANENTLY, response.status());
    }

    @Test
    public void responseLocationHeader() throws Exception {
        Response response = new Redirect().response(getRequest);
        assertTrue("Location header is 'http://localhost:5000/'", response.headers().contains(Header.LOCATION + "http://localhost:5000/"));
    }
}