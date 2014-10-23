package javaserver.cobspec.handler;

import javaserver.cobspec.Resources;
import javaserver.core.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class FormUpdateTest {

    @Test
    public void formResourceUpdated() throws Exception {
        Request request = new Request();
        request.setBody("cosby");
        new FormUpdate(request).response();
        assertEquals("Form resource is updated with request body", "cosby", Resources.formResource);
    }
}