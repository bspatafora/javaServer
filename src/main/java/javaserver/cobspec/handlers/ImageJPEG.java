package javaserver.cobspec.handlers;

import javaserver.core.Request;
import javaserver.core.constants.Header;

public class ImageJPEG extends FileHandler {
    public ImageJPEG(Request request) {
        super(request);
        setContentType(Header.IMAGE_JPEG);
        setFileName("image.jpeg");
    }

    protected void get() {
        super.get();
    }
}
