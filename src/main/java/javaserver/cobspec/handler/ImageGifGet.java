package javaserver.cobspec.handler;

import javaserver.core.constants.Header;

public class ImageGifGet extends FileGet {
    public ImageGifGet() {
        setContentType(Header.IMAGE_GIF);
        setFileName("image.gif");
    }
}
