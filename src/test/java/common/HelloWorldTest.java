package common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

    @Test
    public void greeting() {
        assertEquals(HelloWorld.greeting(), "Hello, world!");
    }
}