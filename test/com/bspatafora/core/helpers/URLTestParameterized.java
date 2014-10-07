package com.bspatafora.core.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class URLTestParameterized {
    @Parameterized.Parameters(name = "Test {index}: {0} decodes to \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"%20", " "},
            {"%3C", "<"},
            {"%2C", ","},
            {"%3E", ">"},
            {"%3D", "="},
            {"%3B", ";"},
            {"%2B", "+"},
            {"%26", "&"},
            {"%40", "@"},
            {"%23", "#"},
            {"%24", "$"},
            {"%5B", "["},
            {"%5D", "]"},
            {"%3A", ":"},
            {"%22", "\""},
            {"%3F", "?"}
        });
    }

    private final String percentCode;
    private final String decodedCharacter;

    public URLTestParameterized(String percentCode, String decodedCharacter) {
        this.percentCode = percentCode;
        this.decodedCharacter = decodedCharacter;
    }

    @Test
    public void percentCodeEachPercentCode() {
        assertEquals("Decodes individual percent codes into their respective characters", decodedCharacter, URL.percentCode(percentCode));
    }
}