package org.vladpush.rps.parser;

import org.junit.jupiter.api.Test;
import org.vladpush.rps.handler.FakeTextPrinter;
import org.vladpush.rps.output.TextPrinter;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.vladpush.rps.parser.InputParser.INVALID_NUMBER_PROMPT;
import static org.vladpush.rps.parser.InputParser.MAX_ALLOWED_ROUNDS;

class InputParserTest {

    private InputParser prepareParserWithInput(String input) {
        var inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        return new InputParser(new Scanner(inputStream), new TextPrinter());
    }

    private InputParser prepareParserWithInputAndPrinter(String input, FakeTextPrinter printer) {
        var inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        return new InputParser(new Scanner(inputStream), printer);
    }

    @Test
    void returnsNumberOfRoundsWhenPositiveNumberIsProvided() {
        var rawNumberOfRoundsToParse = "1";
        var parser = prepareParserWithInput(rawNumberOfRoundsToParse);

        var result = parser.getNumberOfRounds();

        var expectedParsedNumberOfRounds = Integer.parseInt(rawNumberOfRoundsToParse);
        var actualParsedNumberOfRounds = result.value();
        assertEquals(expectedParsedNumberOfRounds, actualParsedNumberOfRounds);
    }

    @Test
    void retriesOnNonNumericInputAndParsesValidNumber() {
        var invalidInput = "abc";
        var validNumberOfRoundsToParse = "1";
        var input = invalidInput + "\n" + validNumberOfRoundsToParse + "\n";
        var printer = new FakeTextPrinter();
        var parser = prepareParserWithInputAndPrinter(input, printer);

        var result = parser.getNumberOfRounds();

        var errorMessage = String.format(INVALID_NUMBER_PROMPT, MAX_ALLOWED_ROUNDS, invalidInput);
        var producedErrorMessage = printer.getPrintedLines().stream().anyMatch(l -> l.contains(errorMessage));
        assertTrue(producedErrorMessage);
        var expectedParsedNumberOfRounds = Integer.parseInt(validNumberOfRoundsToParse);
        var actualParsedNumberOfRounds = result.value();
        assertEquals(expectedParsedNumberOfRounds, actualParsedNumberOfRounds);
    }

    @Test
    void retriesWhenNumberExceedsMaxAllowedThenParsesValidNumber() {
        var tooLarge = String.valueOf(MAX_ALLOWED_ROUNDS + 1);
        var valid = String.valueOf(MAX_ALLOWED_ROUNDS);
        var input = tooLarge + "\n" + valid + "\n";
        var printer = new FakeTextPrinter();
        var parser = prepareParserWithInputAndPrinter(input, printer);

        var result = parser.getNumberOfRounds();

        assertEquals(MAX_ALLOWED_ROUNDS, result.value());
        assertTrue(printer.getPrintedLines().stream().anyMatch(l -> l.contains(String.format(INVALID_NUMBER_PROMPT, MAX_ALLOWED_ROUNDS, tooLarge))));
    }

    @Test
    void retriesWhenNumberIsNegativeThenParsesValidNumber() {
        var negativeNumberOfRoundsToParse = "-1";
        var validNumberOfRoundsToParse = "1";
        var input = negativeNumberOfRoundsToParse + "\n" + validNumberOfRoundsToParse + "\n";
        var printer = new FakeTextPrinter();
        var parser = prepareParserWithInputAndPrinter(input, printer);

        var result = parser.getNumberOfRounds();
        var expectedParsedNumberOfRounds = Integer.parseInt(validNumberOfRoundsToParse);
        var actualParsedNumberOfRounds = result.value();
        assertEquals(expectedParsedNumberOfRounds, actualParsedNumberOfRounds);
        assertTrue(printer.getPrintedLines().stream().anyMatch(l -> l.contains(String.format(INVALID_NUMBER_PROMPT, MAX_ALLOWED_ROUNDS, negativeNumberOfRoundsToParse))));
    }

    @Test
    void acceptsZeroAsValidNumber() {
        var parser = prepareParserWithInput("0\n");
        var result = parser.getNumberOfRounds();
        assertEquals(0, result.value());
    }

    @Test
    void trimsWhitespaceBeforeParsing() {
        var parser = prepareParserWithInput("   10   \n");
        var result = parser.getNumberOfRounds();
        assertEquals(10, result.value());
    }
}
