package com.nexjot.nexjot.api.utility;

/**
 * This class is used to slice the given text to the given size.
 */
public class StringSlicer {
    /**
     * This method slices the given text to the given size.
     * @param text The string to slice
     * @param size The size to slice the text to
     * @return sliced text
     */
    public static String sliceString(String text, int size) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        } else if (size < 1) {
            throw new IllegalArgumentException("Size must be non negative");
        }

        return text.length() <= size ? text : text.substring(0, size);
    }
}
