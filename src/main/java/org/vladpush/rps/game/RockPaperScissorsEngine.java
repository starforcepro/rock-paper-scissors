package org.vladpush.rps.game;

import org.vladpush.rps.models.Move;
import org.vladpush.rps.models.Result;

/**
 * Encapsulates the rules of Rock-Paper-Scissors and evaluates outcomes for given moves.
 */
public final class RockPaperScissorsEngine {

    /**
     * Determines the outcome of a round based on two players' moves.
     *
     * @param player1Move move selected by player 1
     * @param player2Move move selected by player 2
     * @return the {@link Result} describing who wins or if it is a draw
     * @throws IllegalStateException if an unexpected move value is encountered
     */
    public Result getRoundResult(Move player1Move, Move player2Move) {
        if (player1Move == player2Move) return Result.DRAW;
        return switch (player1Move) {
            case ROCK -> (player2Move == Move.SCISSORS) ? Result.PLAYER_1_WINS : Result.PLAYER_2_WINS;
            case PAPER -> (player2Move == Move.ROCK) ? Result.PLAYER_1_WINS : Result.PLAYER_2_WINS;
            case SCISSORS -> (player2Move == Move.PAPER) ? Result.PLAYER_1_WINS : Result.PLAYER_2_WINS;
            default -> throw new IllegalStateException("Unexpected value: " + player1Move);
        };
    }
}
