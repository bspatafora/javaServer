package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormDeleteTest {

    @Test
    public void formResourceDeleted() throws Exception {
        Resources.formResource = "cosby";

        new FormDelete().response();
        assertEquals("Form resource is deleted", "", Resources.formResource);
    }
}