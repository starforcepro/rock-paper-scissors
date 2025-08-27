package org.vladpush.rps.handler;

import org.junit.jupiter.api.Test;
import org.vladpush.rps.models.Move;
import org.vladpush.rps.parser.InputParser;
import org.vladpush.rps.player.ComputerPlayer;
import org.vladpush.rps.player.FakeMoveProvider;
import org.vladpush.rps.player.RealPlayer;
import org.vladpush.rps.provider.InputProvider;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.vladpush.rps.game.Round.*;
import static org.vladpush.rps.handler.RockPaperScissorsGameHandler.*;

public class RockPaperScissorsRoundHandlerTest {

    @Test
    void handlesGameWithThreeRoundsAndThreeMovesIncludingUnknownMove() {
        var printer = new FakeTextPrinter();
        var moves = List.of(Move.ROCK, Move.PAPER, Move.UNKNOWN, Move.SCISSORS);
        var requestedRoundsNumber = 3;
        var failedRoundsNumber = 1;
        var totalRoundsNumber = requestedRoundsNumber + failedRoundsNumber;
        var gameSetup = new TestGameSetUp()
                .withRounds(requestedRoundsNumber)
                .withPlayerMoves(moves)
                .build();
        var testInputStream = new ByteArrayInputStream(gameSetup.getBytes());
        var inputParser = new InputParser(new Scanner(testInputStream), printer);
        var player1 = new RealPlayer(new InputProvider(inputParser));
        var player2 = new ComputerPlayer(new FakeMoveProvider(Move.PAPER));

        new RockPaperScissorsGameHandler(printer, inputParser, player1, player2).handle();

        var expectedEntries = buildExpectedOutput(totalRoundsNumber, moves, player1.name(), player2.name());
        assertEquals(expectedEntries, printer.getPrintedLines());
    }

    private ArrayList<String> buildExpectedOutput(Integer roundsNumber, List<Move> moves, String player1Name, String player2Name) {
        var expectedEntries = new ArrayList<String>();
        expectedEntries.add(WELCOME_MESSAGE);
        var currentRound = 1;
        for (var i = 0; i < roundsNumber; i++) {
            expectedEntries.add(String.format(MOVE_PROMPT_MESSAGE, currentRound));
            Move currentPlayerMove = moves.get(i);
            var resultMessage = switch (currentPlayerMove) {
                case PAPER -> DRAW_MESSAGE;
                case ROCK -> String.format(PLAYER_WINS_MESSAGE, "Computer");
                case SCISSORS -> String.format(PLAYER_WINS_MESSAGE, "Player");
                default -> String.format(INVALID_MOVE_PROMPT, "Player");
            };
            expectedEntries.add(String.format(GAME_ROUND_TEMPLATE, player1Name, currentPlayerMove, player2Name, Move.PAPER, resultMessage));
            if (currentPlayerMove != Move.UNKNOWN) {
                currentRound++;
            }
        }
        expectedEntries.add(MESSAGE_EXIT_THANKS);
        return expectedEntries;
    }
}
