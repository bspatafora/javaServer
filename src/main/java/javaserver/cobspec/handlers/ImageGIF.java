package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.constants.Header;

public class ImageGIF extends FileHandler {
    public ImageGIF(Request request) {
        super(request);
        setContentType(Header.IMAGE_GIF);
        setFileName("image.gif");
    }

    protected void get() {
        super.get();
    }
}
