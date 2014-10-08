package com.bspatafora.core.helpers;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class StreamTest {
    @Test
    public void streamToString() throws Exception {
        String content = "Content";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());

        assertEquals("Reads the input stream to a string", content, Stream.toString(inputStream));
    }

    @Test
    public void readPartial() throws Exception {
        String content = "Partial content";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes());

        String partialContent = new String(Stream.readPartial(inputStream, 0, 4));
        assertEquals("Reads the specified bytes from the input stream", "Part", partialContent);
    }
}