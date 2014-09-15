package com.bspatafora.java_server;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class CommandLineTest {

    @Test
    public void parseArgumentsPortFirst() throws Exception {
        String[] args = { "-p", "9000", "-d", "/some_directory/" };
        Map argMap = CommandLine.parseArguments(args);
        Integer port = (Integer) argMap.get("port");
        String directory = (String) argMap.get("directory");
        assertTrue("Port is correctly parsed when first", port == 9000);
        assertTrue("Directory is correctly parsed when second", directory.equals("/some_directory/"));
    }

    @Test
    public void parseArgumentsDirectoryFirst() throws Exception {
        String[] args = { "-d", "/some_directory/", "-p", "9000" };
        Map argMap = CommandLine.parseArguments(args);
        Integer port = (Integer) argMap.get("port");
        String directory = (String) argMap.get("directory");
        assertTrue("Port is correctly parsed when second", port == 9000);
        assertTrue("Directory is correctly parsed when first", directory.equals("/some_directory/"));
    }

    @Test
    public void parseArgumentsMalformed() throws Exception {
        String[] args = { "-r", "incorrect", "-g", "usage" };
        Map argMap = CommandLine.parseArguments(args);
        Integer port = (Integer) argMap.get("port");
        String directory = (String) argMap.get("directory");
        assertTrue("Port defaults to 5000 with malformed args", port == 5000);
        assertTrue("Directory defaults to '/default/directory/' with malformed args", directory.equals("/default/directory/"));
    }

    @Test
    public void parseArgumentsTooFew() throws Exception {
        String[] args = { "-d", "/some_directory/" };
        Map argMap = CommandLine.parseArguments(args);
        Integer port = (Integer) argMap.get("port");
        String directory = (String) argMap.get("directory");
        assertTrue("Port defaults to 5000 with too few args", port == 5000);
        assertTrue("Directory defaults to '/default/directory/' with too few args", directory.equals("/default/directory/"));
    }

    @Test
    public void parseArgumentsPortInvalid() throws Exception {
        String[] args = { "-p", "not_a_number", "-d", "/some_directory/" };
        Map argMap = CommandLine.parseArguments(args);
        Integer port = (Integer) argMap.get("port");
        String directory = (String) argMap.get("directory");
        assertTrue("Port defaults to 5000 with invalid port", port == 5000);
        assertTrue("Directory defaults to '/default/directory/' with invalid port", directory.equals("/default/directory/"));
    }
}