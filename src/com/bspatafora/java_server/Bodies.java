package com.bspatafora.java_server;

public class Bodies {
    public static String NOT_FOUND = "Not found.";
    public static String REDIRECT = "Moved.";

    public static String rootLinks() {
        String file1 = "<a href=\"/file1\">file1</a>";
        String file2 = "<a href=\"/file2\">file2</a>";
        String imageGif = "<a href=\"/image.gif\">image.gif</a>";
        String imageJpeg = "<a href=\"/image.jpeg\">image.jpeg</a>";
        String imagePng = "<a href=\"/image.png\">image.png</a>";
        String text_file = "<a href=\"/text-file.txt\">text-file.txt</a>";
        return file1 + file2 + imageGif + imageJpeg + imagePng + text_file;
    }
}
