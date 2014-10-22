package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.Response;
import javaserver.core.constants.Method;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParametersTest {
    private static final Request queryStringGETRequest = new Request();
    static {
        queryStringGETRequest.setMethod(Method.GET);
        queryStringGETRequest.setRoute("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");
    }

    @Test
    public void responseBody() throws Exception {
        String decodedParameterPair1 = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?";
        String decodedParameterPair2 = "variable_2 = stuff";

        Response response = new Parameters(queryStringGETRequest).response();
        assertTrue("Body contains the first decoded parameter pair", new String(response.body()).contains(decodedParameterPair1));
        assertTrue("Body contains the second decoded parameter pair", new String(response.body()).contains(decodedParameterPair2));
    }
}