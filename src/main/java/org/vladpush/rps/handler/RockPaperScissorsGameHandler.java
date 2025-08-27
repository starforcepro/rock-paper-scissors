package org.vladpush.rps.handler;

import org.vladpush.rps.game.RockPaperScissorsEngine;
import org.vladpush.rps.game.Round;
import org.vladpush.rps.models.Result;
import org.vladpush.rps.output.Printer;
import org.vladpush.rps.parser.InputParser;
import org.vladpush.rps.player.Player;

/**
 * High-level coordinator for the interactive Rock-Paper-Scissors session.
 * <p>
 * Prints greetings, asks for the number of rounds, and loops through rounds until completion.
 */
public class RockPaperScissorsGameHandler {
    private final Printer printer;
    private final InputParser inputParser;
    private final Player player1;
    private final Player player2;

    /**
     * Constructs the game handler with the required collaborators.
     *
     * @param printer     output sink for user-facing messages
     * @param inputParser user input parser
     * @param player1     first player
     * @param player2     second player
     */
    public RockPaperScissorsGameHandler(Printer printer, InputParser inputParser, Player player1, Player player2) {
        this.printer = printer;
        this.inputParser = inputParser;
        this.player1 = player1;
        this.player2 = player2;
    }

    public static final String MESSAGE_EXIT_THANKS = "Thank you for playing!";
    public static final String WELCOME_MESSAGE = """
            Hello, this is Rock-Paper-Scissors round. Please follow the instructions provided below
            You can exit anytime by entering 'exit'
            Please specify number of rounds to play:
            """;

    /**
     * Runs the interactive flow: welcome, collect number of rounds, play all rounds, and farewell.
     */
    public void handle() {
        printer.print(WELCOME_MESSAGE);

        var numberOfRounds = getNumberOfRounds();
        playRounds(numberOfRounds);

        printer.print(MESSAGE_EXIT_THANKS);
    }

    /**
     * Reads and validates the number of rounds from user input.
     *
     * @return a positive integer number of rounds, not exceeding allowed limits
     */
    private Integer getNumberOfRounds() {
        var numberOfRoundsResult = inputParser.getNumberOfRounds();

        return numberOfRoundsResult.value();
    }

    /**
     * Plays the specified number of rounds sequentially, printing results as they occur.
     * Invalid moves cause the round to be repeated without decrementing the remaining rounds.
     *
     * @param numberOfRounds total rounds to play
     */
    private void playRounds(Integer numberOfRounds) {
        var round = new Round(new RockPaperScissorsEngine(), printer, player1, player2);

        var currentRound = 1;
        var roundsRequested = numberOfRounds;
        while (roundsRequested > 0) {
            var roundInfo = round.play(currentRound);
            if (roundInfo.result() == Result.INVALID_MOVE) {
                round.printRoundInfo(roundInfo);
                continue;
            }
            round.printRoundInfo(roundInfo);
            roundsRequested--;
            currentRound++;
        }
    }
}
