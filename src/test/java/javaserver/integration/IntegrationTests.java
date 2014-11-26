package javaserver.integration;

import javaserver.core.*;
import javaserver.core.constants.Status;
import javaserver.core.helpers.Stream;
import org.junit.Test;

import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {
    @Test
    public void respond() throws Exception {
        int port = 9000;
        new Thread(new Server(port, new MockRouterFactory())).start();
        Socket socket = new Socket("localhost", port);
        new PrintWriter(socket.getOutputStream(), true).println("GET /mock HTTP/1.1\r\n");

        String response = Stream.toString(socket.getInputStream());
        assertEquals("It returns a response", "HTTP/1.1 200 OK\r\n\r\nThe response body", response);
    }
}

class MockRouterFactory implements RouterFactory {
    public Router buildRouter() {
        return new MockRouter();
    }
}

class MockRouter implements Router {
    public Response response(Request request) {
        Response response = new Response();
        response.setStatus(Status.OK);
        response.setBody("The response body".getBytes());
        return response;
    }
}
