package javaserver.cobspec.handlers;

import javaserver.core.constants.Header;

public class ImagePNG extends FileHandler {
    public ImagePNG() {
        setContentType(Header.IMAGE_PNG);
        setFileName("image.png");
    }

    protected void get() {
        super.get();
    }
}
