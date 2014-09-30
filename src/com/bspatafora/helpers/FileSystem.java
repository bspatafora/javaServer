package com.bspatafora.helpers;

import java.io.File;

public class FileSystem {
    public static String[] fileNames(String directoryName) {
        File directory = new File(directoryName);
        return directory.list();
    }
}
