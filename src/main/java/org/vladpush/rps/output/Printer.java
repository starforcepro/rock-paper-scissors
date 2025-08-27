package org.vladpush.rps.output;

/**
 * Simple abstraction for printing text to some output sink (console, logs, etc.).
 */
public interface Printer {
    /**
     * Prints the given text without adding a trailing newline.
     *
     * @param text text to print
     */
    void print(String text);
}
