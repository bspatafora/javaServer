package main.java.javaserver.cobspec.handlers;

import main.java.javaserver.core.constants.Header;

public class ImageGIF extends FileHandler {
    public ImageGIF() {
        setContentType(Header.IMAGE_GIF);
        setFileName("image.gif");
    }

    protected void get() {
        super.get();
    }
}
