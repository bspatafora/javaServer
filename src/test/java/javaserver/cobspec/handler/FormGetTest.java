package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import javaserver.core.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class FormGetTest {

    @Test
    public void responseBody() throws Exception {
        Resources.formResource = "cosby";

        Response response = new FormGet().response();
        assertEquals("Body is whatever is stored in the form resource when GET", "cosby", new String(response.body()));
    }
}