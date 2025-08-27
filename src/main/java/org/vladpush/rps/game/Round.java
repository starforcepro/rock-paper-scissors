package org.vladpush.rps.game;

import org.vladpush.rps.models.Move;
import org.vladpush.rps.models.Result;
import org.vladpush.rps.models.RoundInfo;
import org.vladpush.rps.output.Printer;
import org.vladpush.rps.player.Player;

/**
 * Coordinates a single Rock-Paper-Scissors round between two players.
 * <p>
 * Handles prompting, move acquisition, validation, result calculation, and printing.
 */
public class Round {
    public static final String MOVE_PROMPT_MESSAGE = "Round %d - enter your move (rock or r, paper or p, scissors or s): ";
    public static final String PLAYER_WINS_MESSAGE = "%s wins!";
    public static final String DRAW_MESSAGE = "Draw.";
    public static final String GAME_ROUND_TEMPLATE = "%s: %s, %s: %s -> %s%n";
    public static final String INVALID_MOVE_PROMPT = "Invalid move for %s, round reset, please try again.";

    private final RockPaperScissorsEngine engine;
    public final Printer printer;
    private final Player player1;
    private final Player player2;

    /**
     * Creates a new round coordinator.
     *
     * @param engine  engine that determines the outcome of moves
     * @param printer sink to output user-facing messages
     * @param player1 first player (e.g., real person)
     * @param player2 second player (e.g., computer)
     */
    public Round(RockPaperScissorsEngine engine, Printer printer, Player player1, Player player2) {
        this.engine = engine;
        this.printer = printer;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Runs a single round by prompting players, collecting moves, and producing the outcome.
     *
     * @param round the sequential round number (1-based) used in prompts
     * @return a {@link RoundInfo} structure describing the round outcome
     */
    public RoundInfo play(Integer round) {
        printer.print(String.format(MOVE_PROMPT_MESSAGE, round));
        var player1Move = player1.makeMove();
        var player2Move = player2.makeMove();
        if (player1Move == Move.UNKNOWN) {
            return new RoundInfo(player1Move, player2Move, Result.INVALID_MOVE, player1.name());
        }
        if (player2Move == Move.UNKNOWN) {
            return new RoundInfo(player1Move, player2Move, Result.INVALID_MOVE, player2.name());
        }
        var roundResult = engine.getRoundResult(player1Move, player2Move);
        return new RoundInfo(player1Move, player2Move, roundResult, "");
    }

    /**
     * Prints the provided round information to the configured {@link Printer} in a human-readable format.
     *
     * @param roundInfo the outcome details to print
     */
    public void printRoundInfo(RoundInfo roundInfo) {
        var roundResult = switch (roundInfo.result()) {
            case PLAYER_1_WINS -> String.format(PLAYER_WINS_MESSAGE, player1.name());
            case PLAYER_2_WINS -> String.format(PLAYER_WINS_MESSAGE, player2.name());
            case DRAW -> DRAW_MESSAGE;
            case INVALID_MOVE -> String.format(INVALID_MOVE_PROMPT, roundInfo.invalidMovePlayerName());
        };
        printer.print(String.format(GAME_ROUND_TEMPLATE, player1.name(), roundInfo.player1Move(), player2.name(), roundInfo.player2Move(), roundResult));
    }
}
