package com.bspatafora.core.helpers;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileSystemTest {
    @Test
    public void fileNames() throws Exception {
        String directory = "/";
        String[] fileNames = new File(directory).list();
        assertTrue("It returns a string array of the file names in the passed directory", Arrays.equals(fileNames, FileSystem.fileNames(directory)));
    }
}