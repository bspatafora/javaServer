package com.bspatafora.java_server;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.*;

public class WorkerTest {
    private static final String getRoot = "GET / HTTP/1.1\r\n";
    private static final String postForm = "POST /form HTTP/1.1\r\nContent-Length: 10\r\n\r\ndata=cosby\r\n";
    private static final String putForm = "PUT /form HTTP/1.1\r\nContent-Length: 15\r\n\r\ndata=heathcliff\r\n";
    private static final String getForm = "GET /form HTTP/1.1\r\n";
    private static final String deleteFormRequest = "DELETE /form HTTP/1.1\r\n";

    @BeforeClass
    public static void startServer() {
        new Thread(new Server(9000)).start();
    }

    @Test
    public void getRootStatus() throws Exception {
        Socket getRootSocket = new Socket("localhost", 9000);
        PrintWriter toServer = new PrintWriter(getRootSocket.getOutputStream(), true);
        toServer.println(getRoot);

        String getRootResponse = Stream.toString(getRootSocket.getInputStream());
        assertTrue("Response to GET / is '200 OK'", getRootResponse.contains("200 OK"));
    }

    @Test
    public void getRootBody() throws Exception {
        Socket getRootSocket = new Socket("localhost", 9000);
        PrintWriter toServer = new PrintWriter(getRootSocket.getOutputStream(), true);
        toServer.println(getRoot);

        String getRootResponse = Stream.toString(getRootSocket.getInputStream());
        assertTrue("Response to GET / has body 'Hello, World!'", getRootResponse.contains("Hello, world!"));
    }

    @Test
    public void postFormStatus() throws Exception {
        Socket postFormSocket = new Socket("localhost", 9000);
        PrintWriter toServer = new PrintWriter(postFormSocket.getOutputStream(), true);
        toServer.println(postForm);

        String postFormResponse = Stream.toString(postFormSocket.getInputStream());
        assertTrue("Response to POST /form is '200 OK'", postFormResponse.contains("200 OK"));
    }

    @Test
    public void postGet() throws Exception {
        Socket postFormSocket = new Socket("localhost", 9000);
        PrintWriter postToServer = new PrintWriter(postFormSocket.getOutputStream(), true);
        postToServer.println(postForm);

        Socket getFormSocket = new Socket("localhost", 9000);
        PrintWriter getToServer = new PrintWriter(getFormSocket.getOutputStream(), true);
        getToServer.println(getForm);

        String getFormResponse = Stream.toString(getFormSocket.getInputStream());
        assertTrue("Response to GET /form contains previously POSTed data, 'data=cosby'", getFormResponse.contains("data=cosby"));
    }

    @Test
    public void putGetDeleteGet() throws Exception {
        Socket putFormSocket = new Socket("localhost", 9000);
        PrintWriter putToServer = new PrintWriter(putFormSocket.getOutputStream(), true);
        putToServer.println(putForm);

        String putFormResponse = Stream.toString(putFormSocket.getInputStream());
        assertTrue("Response to PUT /form is '200 OK'", putFormResponse.contains("200 OK"));

        Socket getFormSocket1 = new Socket("localhost", 9000);
        PrintWriter getToServer1 = new PrintWriter(getFormSocket1.getOutputStream(), true);
        getToServer1.println(getForm);

        String getFormResponse1 = Stream.toString(getFormSocket1.getInputStream());
        assertTrue("Response to GET /form contains previously PUT data, 'data=heathcliff'", getFormResponse1.contains("data=heathcliff"));

        Socket deleteFormSocket = new Socket("localhost", 9000);
        PrintWriter deleteToServer = new PrintWriter(deleteFormSocket.getOutputStream(), true);
        deleteToServer.println(deleteFormRequest);

        Socket getFormSocket2 = new Socket("localhost", 9000);
        PrintWriter getToServer2 = new PrintWriter(getFormSocket2.getOutputStream(), true);
        getToServer2.println(getForm);

        String getFormResponse2 = Stream.toString(getFormSocket2.getInputStream());
        assertFalse("Response to GET /form does not contain DELETEd PUT data", getFormResponse2.contains("data=heathcliff"));
    }
}