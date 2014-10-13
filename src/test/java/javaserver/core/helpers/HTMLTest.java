package javaserver.core.helpers;

import org.junit.Assert;
import org.junit.Test;

public class HTMLTest {

    @Test
    public void link() throws Exception {
        Assert.assertEquals("It returns an HTML link based on the passed address and name", "<a href=\"/address\">Name</a>", HTML.link("address", "Name"));
    }
}