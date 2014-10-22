package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.constants.Header;

public class ImagePNG extends FileHandler {
    public ImagePNG(Request request) {
        super(request);
        setContentType(Header.IMAGE_PNG);
        setFileName("image.png");
    }

    protected void get() {
        super.get();
    }
}
