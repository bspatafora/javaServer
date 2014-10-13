package test.java.javaserver.core.helpers;

import main.java.javaserver.core.constants.Method;
import main.java.javaserver.core.helpers.HTTP;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HTTPTest {
    @Test
    public void allowedMethodsWhenNone() throws Exception {
        Assert.assertEquals("Is empty when class has no HTTP methods", "", HTTP.allowedMethods(Object.class));
    }

    @Test
    public void allowedMethodsWhenGet() throws Exception {
        assertEquals("Is 'GET' when class has only a get method", Method.GET, HTTP.allowedMethods(hasGet.class));
    }

    @Test
    public void allowedMethodsWhenGetHeadOptions() throws Exception {
        assertEquals("Is 'GET, HEAD, OPTIONS' when class has those methods", "GET, HEAD, OPTIONS", HTTP.allowedMethods(hasGetHeadOptions.class));
    }

    private class hasGet {
        private void get() {}
    }

    private class hasGetHeadOptions {
        private void get() {}
        private void head() {}
        private void options() {}
    }
}