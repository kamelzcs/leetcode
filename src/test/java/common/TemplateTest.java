package common;

import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemplateTest {

    @Test
    public void replace() {
        Assert.assertEquals("aabccabc", Template.replace("a{A}c{A}", ImmutableMap.of("A", "abc")));
        Assert.assertEquals("aabccbcd", Template.replace("a{A}c{ABC}", ImmutableMap.of("A", "abc", "ABC", "bcd")));
        Assert.assertEquals("aaca", Template.replace("a{A}c{{ABC}}", ImmutableMap.of("A", "a", "ABC", "A")));
    }
}