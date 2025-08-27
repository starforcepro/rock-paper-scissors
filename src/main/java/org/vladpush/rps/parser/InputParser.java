package org.vladpush.rps.parser;

import org.vladpush.rps.models.InputParserResult;
import org.vladpush.rps.models.Move;
import org.vladpush.rps.output.Printer;

import java.util.Scanner;

import static org.vladpush.rps.handler.RockPaperScissorsGameHandler.MESSAGE_EXIT_THANKS;

/**
 * Parses user input from a {@link Scanner} and converts it into domain values.
 * Supports exiting the application by typing the keyword {@code exit}.
 */
public class InputParser {
    public static final String INVALID_NUMBER_PROMPT = "Positive number up to %d is expected, but got: '%s'. Please try again: ";
    public static final String EXIT = "exit";
    public static final int MAX_ALLOWED_ROUNDS = 1000;

    /** Input source to read from. */
    public final Scanner scanner;
    /** Printer used to show prompts and validation messages. */
    public Printer printer;

    /**
     * Creates a new input parser.
     *
     * @param scanner input source (e.g., System.in wrapped in Scanner)
     * @param printer printer for feedback messages
     */
    public InputParser(Scanner scanner, Printer printer) {
        this.scanner = scanner;
        this.printer = printer;
    }

    /**
     * Reads, validates, and returns the number of rounds to play.
     * Accepts unsigned integers from 0 to {@link #MAX_ALLOWED_ROUNDS} (inclusive).
     * Prints a validation message and keeps asking until the value is valid or the user types {@link #EXIT}.
     *
     * @return wrapper containing the validated number of rounds
     */
    public InputParserResult<Integer> getNumberOfRounds() {
        var roundsRequested = -1;
        while (roundsRequested < 0 || roundsRequested > MAX_ALLOWED_ROUNDS) {
            var input = readUserInput();
            exitIfNeeded(input);
            try {
                roundsRequested = Integer.parseUnsignedInt(input);
                if (roundsRequested > MAX_ALLOWED_ROUNDS) {
                    throw new NumberFormatException();
                }
            } catch (Exception e) {
                printer.print(String.format(INVALID_NUMBER_PROMPT, MAX_ALLOWED_ROUNDS, input));
            }
        }

        return new InputParserResult<>(roundsRequested);
    }

    /**
     * Reads a single move from user input and converts it to a {@link Move} instance.
     * Allows exiting the application by entering {@link #EXIT}.
     *
     * @return wrapper containing the parsed move (may be {@link Move#UNKNOWN})
     */
    public InputParserResult<Move> getMove() {
        var playerMoveInput = readUserInput();
        var playerMove = Move.parse(playerMoveInput);
        exitIfNeeded(playerMoveInput);

        return new InputParserResult<>(playerMove);
    }

    /**
     * Reads one line of user input, trimming and lowercasing it for simplified matching.
     *
     * @return normalized input line
     */
    private String readUserInput() {
        return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * Terminates the application if the user typed {@link #EXIT}.
     * Prints a farewell message before exiting.
     *
     * @param input the raw input string to check
     */
    private void exitIfNeeded(String input) {
        if (input.equals(EXIT)) {
            printer.print(MESSAGE_EXIT_THANKS);
            System.exit(0);
        }
    }
}

