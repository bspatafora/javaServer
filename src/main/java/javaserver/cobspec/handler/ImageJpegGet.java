package javaserver.cobspec.handler;

import javaserver.core.constants.Header;

public class ImageJpegGet extends FileGet {
    public ImageJpegGet() {
        setContentType(Header.IMAGE_JPEG);
        setFileName("image.jpeg");
    }
}
