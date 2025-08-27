package org.vladpush.rps.models;

/**
 * Outcome of a round.
 */
public enum Result {
    /** Player 1 defeats player 2. */
    PLAYER_1_WINS,
    /** Player 2 defeats player 1. */
    PLAYER_2_WINS,
    /** Both players chose the same move. */
    DRAW,
    /** At least one player's move was invalid (unknown). */
    INVALID_MOVE
}
