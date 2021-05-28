package com.github.bfu4.hyperplay.objects;

import com.github.bfu4.hyperplay.interfaces.QuickSet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SetTest {

    /**
     * The set used
     */
    private QuickSet<String> quickset;

    /**
     * Create the set
     */
    @Before
    public void setup() {
        quickset = new QuickSet<>();
    }

    /**
     * Remove all objects from the set
     */
    @After
    public void breakDown() {
        for (int i = 0; i < quickset.size(); i ++) {
            quickset.remove(quickset.get(i));
        }
        quickset = null;
    }

    /**
     * Get a null array
     */
    @Test
    public void getArrayAssertNull() {
        Assert.assertNull(quickset.toArray(null));
    }

    /**
     * Get an array
     */
    @Test
    public void getArray() {
        quickset.add("hi");
        Assert.assertNotNull(quickset.toArray(new String[0]));
        Assert.assertEquals(quickset.toArray(new String[0])[0], "hi");
    }

    /**
     * Test retrieval of stream object
     */
    @Test
    public void getStream() {
        Assert.assertNotNull(quickset.stream());
    }

    /**
     * Test anti duplication
     */
    @Test
    public void testAddNoDupe() {
        quickset.add("hi");
        Assert.assertEquals(quickset.size(), 1);
        quickset.add("hi");
        Assert.assertEquals(quickset.size(), 1);
        quickset.add("hello");
        Assert.assertEquals(quickset.size(), 2);
    }

}
