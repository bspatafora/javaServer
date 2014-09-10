package com.bspatafora.java_server;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class CommandLineTest {

    @Test
    public void testParseArgumentsPortFirst() throws Exception {
        String[] args = { "-p", "5000", "-d", "/some_directory/" };
        Map argMap = CommandLine.parseArguments(args);
        Integer port = (Integer) argMap.get("port");
        String directory = (String) argMap.get("directory");
        assertTrue("Port is correctly parsed when first", port == 5000);
        assertTrue("Directory is correctly parsed when second", directory.equals("/some_directory/"));
    }

    @Test
    public void testParseArgumentsDirectoryFirst() throws Exception {
        String[] args = { "-d", "/some_directory/", "-p", "5000" };
        Map argMap = CommandLine.parseArguments(args);
        Integer port = (Integer) argMap.get("port");
        String directory = (String) argMap.get("directory");
        assertTrue("Port is correctly parsed when second", port == 5000);
        assertTrue("Directory is correctly parsed when first", directory.equals("/some_directory/"));
    }
}