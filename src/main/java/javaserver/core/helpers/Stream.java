package javaserver.core.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Stream {
    public static String toString(java.io.InputStream inputStream) {
        java.util.Scanner scanner = new java.util.Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    public static byte[] readPartial(InputStream inputStream, int startByteIndex, int endByteIndex) {
        int bytesToRead = endByteIndex - startByteIndex;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            inputStream.skip(startByteIndex);
            for (int i = 0; i < bytesToRead; i++) {
                outputStream.write(inputStream.read());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return outputStream.toByteArray();
    }
}
