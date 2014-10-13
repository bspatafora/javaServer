package javaserver.core.helpers;

import java.io.*;
import java.nio.file.Files;

public class FileSystem {
    public static String[] directoryContents(String directoryName) {
        return new File(directoryName).list();
    }

    public static byte[] read(File file) {
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return fileBytes;
    }

    public static byte[] readPartial(File file, int start, int end) {
        byte[] fileBytes = new byte[0];
        try {
            fileBytes = Stream.readPartial(new FileInputStream(file), start, end);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return fileBytes;
    }
}
