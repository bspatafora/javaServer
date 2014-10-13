package test.java.javaserver.cobspec.handlers;

import main.java.javaserver.cobspec.handlers.Root;
import main.java.javaserver.core.constants.Header;
import main.java.javaserver.core.Request;
import main.java.javaserver.core.Response;
import main.java.javaserver.core.constants.Method;
import main.java.javaserver.core.constants.Status;
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
        String link = "<a href=\"/text-file.txt\">text-file.txt</a></br>";

        Response response = new Root().response(getRequest);
        assertTrue("Body contains CobSpec link", new String(response.body()).contains(link));
    }
}