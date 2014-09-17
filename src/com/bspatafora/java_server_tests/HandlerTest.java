package com.bspatafora.java_server_tests;

import com.bspatafora.java_server.Server;
import com.bspatafora.java_server.StatusLine;
import com.bspatafora.java_server.Stream;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.*;

public class HandlerTest {
    private static final int multiThreadedPort = 9000;
    private static final int singleThreadedPort = 8000;
    private static final String localHost = "localhost";
    private static final String getRoot = "GET / HTTP/1.1\r\n";
    private static final String postForm = "POST /form HTTP/1.1\r\nContent-Length: 10\r\n\r\ndata=cosby\r\n";
    private static final String putForm = "PUT /form HTTP/1.1\r\nContent-Length: 15\r\n\r\ndata=heathcliff\r\n";
    private static final String getForm = "GET /form HTTP/1.1\r\n";
    private static final String deleteForm = "DELETE /form HTTP/1.1\r\n";
    private static final String getUnregistered = "GET /unregistered HTTP/1.1\r\n";

    @BeforeClass
    public static void startServers() {
        new Thread(new Server(multiThreadedPort, true)).start();
        new Thread(new Server(singleThreadedPort, false)).start();
    }

    @Test
    public void getRootStatus() throws Exception {
        Socket getRootSocket = new Socket(localHost, multiThreadedPort);
        PrintWriter toServer = new PrintWriter(getRootSocket.getOutputStream(), true);
        toServer.println(getRoot);

        String getRootResponse = Stream.toString(getRootSocket.getInputStream());
        assertTrue("Response to GET / is '200 OK'", getRootResponse.contains(StatusLine.OK));
    }

    @Test
    public void getRootBody() throws Exception {
        Socket getRootSocket = new Socket(localHost, multiThreadedPort);
        PrintWriter toServer = new PrintWriter(getRootSocket.getOutputStream(), true);
        toServer.println(getRoot);

        String getRootResponse = Stream.toString(getRootSocket.getInputStream());
        assertTrue("Response to GET / has body 'Hello, World!'", getRootResponse.contains("Hello, world!"));
    }

    @Test
    public void postFormStatus() throws Exception {
        Socket postFormSocket = new Socket(localHost, multiThreadedPort);
        PrintWriter toServer = new PrintWriter(postFormSocket.getOutputStream(), true);
        toServer.println(postForm);

        String postFormResponse = Stream.toString(postFormSocket.getInputStream());
        assertTrue("Response to POST /form is '200 OK'", postFormResponse.contains(StatusLine.OK));
    }

    @Test
    public void postGet() throws Exception {
        Socket postFormSocket = new Socket(localHost, singleThreadedPort);
        PrintWriter postToServer = new PrintWriter(postFormSocket.getOutputStream(), true);
        postToServer.println(postForm);

        Socket getFormSocket = new Socket(localHost, singleThreadedPort);
        PrintWriter getToServer = new PrintWriter(getFormSocket.getOutputStream(), true);
        getToServer.println(getForm);

        String getFormResponse = Stream.toString(getFormSocket.getInputStream());
        assertTrue("Response to GET /form contains previously POSTed data, 'data=cosby'", getFormResponse.contains("data=cosby"));
    }

    @Test
    public void putGetDeleteGet() throws Exception {
        Socket putFormSocket = new Socket(localHost, singleThreadedPort);
        PrintWriter putToServer = new PrintWriter(putFormSocket.getOutputStream(), true);
        putToServer.println(putForm);

        String putFormResponse = Stream.toString(putFormSocket.getInputStream());
        assertTrue("Response to PUT /form is '200 OK'", putFormResponse.contains(StatusLine.OK));

        Socket getFormSocket1 = new Socket(localHost, singleThreadedPort);
        PrintWriter getToServer1 = new PrintWriter(getFormSocket1.getOutputStream(), true);
        getToServer1.println(getForm);

        String getFormResponse1 = Stream.toString(getFormSocket1.getInputStream());
        assertTrue("Response to GET /form contains previously PUT data, 'data=heathcliff'", getFormResponse1.contains("data=heathcliff"));

        Socket deleteFormSocket = new Socket(localHost, singleThreadedPort);
        PrintWriter deleteToServer = new PrintWriter(deleteFormSocket.getOutputStream(), true);
        deleteToServer.println(deleteForm);

        // Thread.sleep(5);

        Socket getFormSocket2 = new Socket(localHost, singleThreadedPort);
        PrintWriter getToServer2 = new PrintWriter(getFormSocket2.getOutputStream(), true);
        getToServer2.println(getForm);

        String getFormResponse2 = Stream.toString(getFormSocket2.getInputStream());
        assertFalse("Response to GET /form does not contain DELETEd PUT data", getFormResponse2.contains("data=heathcliff"));
    }

    @Test
    public void getUnregisteredStatus() throws Exception {
        Socket getRootSocket = new Socket(localHost, multiThreadedPort);
        PrintWriter toServer = new PrintWriter(getRootSocket.getOutputStream(), true);
        toServer.println(getUnregistered);

        String getRootResponse = Stream.toString(getRootSocket.getInputStream());
        assertTrue("Response to GET /unregistered is '404 Not Found'", getRootResponse.contains(StatusLine.NOT_FOUND));
    }

    @Test
    public void getUnregisteredBody() throws Exception {
        Socket getRootSocket = new Socket(localHost, multiThreadedPort);
        PrintWriter toServer = new PrintWriter(getRootSocket.getOutputStream(), true);
        toServer.println(getUnregistered);

        String getRootResponse = Stream.toString(getRootSocket.getInputStream());
        assertTrue("Response to GET /unregistered has body 'Not found.'", getRootResponse.contains(StatusLine.NOT_FOUND));
    }
}
