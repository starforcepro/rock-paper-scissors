package org.vladpush.rps.provider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.vladpush.rps.models.Move;
import org.vladpush.rps.output.TextPrinter;
import org.vladpush.rps.parser.InputParser;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputProviderTest {

    private InputProvider providerWithInput(String input) {
        var is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        var parser = new InputParser(new Scanner(is), new TextPrinter());
        return new InputProvider(parser);
    }

    @ParameterizedTest
    @ValueSource(strings = {"p", "paper"})
    void returnsPaperWhenPaperKeyIsProvided(String input) {
        var provider = providerWithInput(input);
        assertEquals(Move.PAPER, provider.move());
    }

    @ParameterizedTest
    @ValueSource(strings = {"r", "rock"})
    void returnsRockWhenRockKeyIsProvided(String input) {
        var provider = providerWithInput(input);
        assertEquals(Move.ROCK, provider.move());
    }

    @ParameterizedTest
    @ValueSource(strings = {"s", "scissors"})
    void returnsScissorsWhenScissorsKeyIsProvided(String input) {
        var provider = providerWithInput(input);
        assertEquals(Move.SCISSORS, provider.move());
    }

    @Test
    void trimsAndIgnoresCase() {
        var provider = providerWithInput("   PaPer   \n");
        assertEquals(Move.PAPER, provider.move());
    }

    @Test
    void returnsUnknownOnInvalid() {
        var provider = providerWithInput("x\n");
        assertEquals(Move.UNKNOWN, provider.move());
    }

    @Test
    void sequentialCallsReadSequentialInputs() {
        var provider = providerWithInput("r\np\ns\n");
        assertEquals(Move.ROCK, provider.move());
        assertEquals(Move.PAPER, provider.move());
        assertEquals(Move.SCISSORS, provider.move());
    }
}
