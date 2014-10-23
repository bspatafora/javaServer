package javaserver.cobspec;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RouterTest {
    @Test
    public void testMethodsAtRoute() {
        ArrayList<String> expectedMethods = new ArrayList<>();
        expectedMethods.add("GET");
        expectedMethods.add("HEAD");
        expectedMethods.add("POST");
        expectedMethods.add("PUT");
        expectedMethods.add("OPTIONS");

        ArrayList<String> actualMethods = new Router().methodsAtRoute("/method_options");
        for (String method : expectedMethods) {
            assertTrue("Actual methods contain all expected methods", actualMethods.contains(method));
        }
        for (String method : actualMethods) {
            assertTrue("Expected methods contain all actual methods", expectedMethods.contains(method));
        }
    }
}