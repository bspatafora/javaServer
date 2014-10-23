package javaserver.cobspec.handler;

import javaserver.core.constants.Header;

public class ImagePngGet extends FileGet {
    public ImagePngGet() {
        setContentType(Header.IMAGE_PNG);
        setFileName("image.png");
    }
}
