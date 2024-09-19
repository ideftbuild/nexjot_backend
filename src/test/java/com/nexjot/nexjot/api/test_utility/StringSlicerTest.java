package com.nexjot.nexjot.api.test_utility;

import com.nexjot.nexjot.api.utility.StringSlicer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringSlicerTest {

    /**
     * Test the sliceString method in the StringSlicer class
     */
    @Test
    public void testStringSlice() {
        String longText = "retsonr rteoinrst  rtinrs t rotnr strtonrs tr r" +
                "rotinrsio trtiosnt rtnrtrsiot rstiornst osrnt rtoinrst";

        String shortText = StringSlicer.sliceString(longText, 40);
        assertEquals(shortText.length(), 40);
    }

    /**
     * Test the sliceString method in the StringSlicer class when null is passed
     */
    @Test
    public void testStringSliceThrowExceptionForNullText() {
        assertThrows(IllegalArgumentException.class, () -> StringSlicer.sliceString(null, 10));
    }

    /**
     * Test the sliceString method in the StringSlicer class when negative
     * size is passed
     */
    @Test
    public void testStringSliceThrowsExceptionForNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> StringSlicer.sliceString("text", -1));
    }
}
