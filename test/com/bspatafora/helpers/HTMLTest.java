package com.bspatafora.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class HTMLTest {

    @Test
    public void link() throws Exception {
        assertEquals("It returns an HTML link based on the passed address and name", "<a href=\"/address\">Name</a>", HTML.link("address", "Name"));
    }
}