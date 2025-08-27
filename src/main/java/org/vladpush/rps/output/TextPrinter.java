package org.vladpush.rps.output;

/**
 * Printer implementation that writes directly to standard output.
 */
public class TextPrinter implements Printer {
    /**
     * Prints the given text to {@link System#out} without a trailing newline.
     *
     * @param text text to print
     */
    public void print(String text) {
        System.out.print(text);
    }
}
