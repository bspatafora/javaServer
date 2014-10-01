package com.bspatafora.core.helpers;

import com.bspatafora.core.Settings;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineTest {
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
        assertTrue("Port is set to default with malformed args", commandLine.port() == Settings.port);
        assertTrue("Directory is set to default with malformed args", commandLine.directory().equals(Settings.directory));
    }

    @Test
    public void parseArgumentsTooFew() throws Exception {
        String[] args = { "-d", "/some_directory/" };

        CommandLine commandLine = new CommandLine(args);
        assertTrue("Port is set to default with too few args", commandLine.port() == Settings.port);
        assertTrue("Directory is set to default with too few args", commandLine.directory().equals(Settings.directory));
    }

    @Test
    public void parseArgumentsPortInvalid() throws Exception {
        String[] args = { "-p", "not_a_number", "-d", "/some_directory/" };

        CommandLine commandLine = new CommandLine(args);
        assertTrue("Port is set to default with invalid port", commandLine.port() == Settings.port);
        assertTrue("Directory is set to default with invalid port", commandLine.directory().equals(Settings.directory));
    }
}
