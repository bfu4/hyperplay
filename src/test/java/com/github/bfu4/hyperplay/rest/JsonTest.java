package com.github.bfu4.hyperplay.rest;

import org.junit.Assert;
import org.junit.Test;

public class JsonTest {

    /**
     * Test the stringification of a singular primitive object
     */
    @Test
    public void testPrimitiveStringify() {
        // Simple name: Integer
        int primitive = 3;
        Assert.assertEquals("{\"Integer\":\"3\"}", JSON.stringify(primitive));
    }

    @Test
    public void testNonPrimitiveStringify() {
        // Simple name: String
        String nonPrimitive = "value";
        Assert.assertEquals("{\"String\":\"value\"}", JSON.stringify(nonPrimitive));
    }

    /**
     * Test stringification when provided a key
     */
    @Test
    public void testStringifyKey() {
        String key = "key";
        String value = "value";
        // Does not auto wrap
        Assert.assertEquals("\"key\":\"value\"", JSON.stringify(key, value));
    }

    /**
     * Test the stringification of multiple objects
     */
    @Test
    public void testStringifyMultiple() {
        String data = JSON.stringify(
                "key",
                new JSONObject("annotation", Test.class),
                new JSONObject("array", Object[].class),
                new JSONObject("builder", new StringBuilder("hi"))
        );
        Assert.assertEquals("{\"key\":\"{\"annotation\":\"interface org.junit.Test\",\"array\":\"class [Ljava.lang.Object;\",\"builder\":\"hi\"}\"}", data);
    }

}
