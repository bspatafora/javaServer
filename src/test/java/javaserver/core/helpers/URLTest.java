package javaserver.core.helpers;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class URLTest {
    @Test
    public void percentCodeEncodedString() {
        String encodedString = "Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F";
        String decodedString = "Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?";

        Assert.assertEquals("Decodes a complex string containing various percent codes", decodedString, URL.percentCode(encodedString));
    }

    @Test
    public void urlParametersSingle() {
        String url = "/something?theKey=theValue";
        HashMap<String, String> urlParameters = new HashMap<>();
        urlParameters.put("theKey", "theValue");

        assertEquals("Returns a parameter hash when there’s one name/value pair", urlParameters, URL.urlParameters(url));
    }

    @Test
    public void urlParametersDouble() {
        String url = "/something?theKey=theValue&anotherKey=anotherValue";
        HashMap<String, String> urlParameters = new HashMap<>();
        urlParameters.put("theKey", "theValue");
        urlParameters.put("anotherKey", "anotherValue");

        assertEquals("Returns a parameter hash when there are two name/value pairs", urlParameters, URL.urlParameters(url));
    }
}
