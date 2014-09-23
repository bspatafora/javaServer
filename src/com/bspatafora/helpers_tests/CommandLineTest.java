package com.bspatafora.helpers_tests;

import com.bspatafora.helpers.CommandLine;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineTest {
    private static final int defaultPort = 5000;
    private static final String defaultDirectory = "/default/directory/";

    @Test
    public void parseArgumentsPortFirst() throws Exception {
        String[] args = { "-p", "9000", "-d", "/some_directory/" };

        CommandLine commandLine = new CommandLine(args);
        assertTrue("Port is correctly parsed when first", commandLine.port() == 9000);
        assertTrue("Directory is correctly parsed when second", commandLine.directory().equals("/some_directory/"));
    }

    @Test
    public void parseArgumentsDirectoryFirst() throws Exception {
        String[] args = { "-d", "/some_directory/", "-p", "9000" };

        CommandLine commandLine = new CommandLine(args);
        assertTrue("Port is correctly parsed when second", commandLine.port() == 9000);
        assertTrue("Directory is correctly parsed when first", commandLine.directory().equals("/some_directory/"));
    }

    @Test
    public void parseArgumentsMalformed() throws Exception {
        String[] args = { "-r", "incorrect", "-g", "usage" };

        CommandLine commandLine = new CommandLine(args);
        assertTrue("Port is set to default with malformed args", commandLine.port() == defaultPort);
        assertTrue("Directory is set to default with malformed args", commandLine.directory().equals(defaultDirectory));
    }

    @Test
    public void parseArgumentsTooFew() throws Exception {
        String[] args = { "-d", "/some_directory/" };

        CommandLine commandLine = new CommandLine(args);
        assertTrue("Port is set to default with too few args", commandLine.port() == defaultPort);
        assertTrue("Directory is set to default with too few args", commandLine.directory().equals(defaultDirectory));
    }

    @Test
    public void parseArgumentsPortInvalid() throws Exception {
        String[] args = { "-p", "not_a_number", "-d", "/some_directory/" };

        CommandLine commandLine = new CommandLine(args);
        assertTrue("Port is set to default with invalid port", commandLine.port() == defaultPort);
        assertTrue("Directory is set to default with invalid port", commandLine.directory().equals(defaultDirectory));
    }
}
