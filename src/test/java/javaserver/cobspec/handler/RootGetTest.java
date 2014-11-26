package javaserver.cobspec.handler;

import javaserver.core.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class RootGetTest {
    @Test
    public void responseBody() throws Exception {
        String link = "<a href=\"/text-file.txt\">text-file.txt</a></br>";

        Response response = new RootGet().response();
        assertTrue("Body contains CobSpec link", new String(response.body()).contains(link));
    }
}