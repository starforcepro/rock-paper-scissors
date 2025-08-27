package org.vladpush.rps.models;

/**
 * Represents possible moves in Rock-Paper-Scissors.
 */
public enum Move {
    /** Rock beats Scissors. */
    ROCK,
    /** Paper beats Rock. */
    PAPER,
    /** Scissors beat Paper. */
    SCISSORS,
    /** Unrecognized input. */
    UNKNOWN;

    /**
     * Parses a textual representation into a {@link Move}.
     * Accepts full names or short aliases (case-insensitive):
     * r/rock, p/paper, s/scissors.
     *
     * @param text input text to parse
     * @return parsed {@link Move} or {@link #UNKNOWN} when not recognized
     * @throws IllegalArgumentException if input is null
     */
    public static Move parse(String text) {
        if (text == null) throw new IllegalArgumentException("Input is null");
        text = text.trim().toLowerCase();
        return switch (text) {
            case "r", "rock" -> ROCK;
            case "p", "paper" -> PAPER;
            case "s", "scissors" -> SCISSORS;
            default -> UNKNOWN;
        };
    }
}
