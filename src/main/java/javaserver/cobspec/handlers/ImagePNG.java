package main.java.javaserver.cobspec.handlers;

import main.java.javaserver.core.constants.Header;

public class ImagePNG extends FileHandler {
    public ImagePNG() {
        setContentType(Header.IMAGE_PNG);
        setFileName("image.png");
    }

    protected void get() {
        super.get();
    }
}
