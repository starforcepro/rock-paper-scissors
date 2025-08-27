package org.vladpush.rps.handler;

import org.vladpush.rps.output.Printer;

import java.util.ArrayList;
import java.util.List;

public class FakeTextPrinter implements Printer {
    private final List<String> printedLines = new ArrayList<>();

    public void print(String text) {
        printedLines.add(text);
    }

    public List<String> getPrintedLines() {
        return printedLines;
    }
}
