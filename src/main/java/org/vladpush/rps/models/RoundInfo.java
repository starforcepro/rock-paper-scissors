package org.vladpush.rps.models;

/**
 * Immutable data holder for a single round outcome.
 *
 * @param player1Move            the move chosen by player 1
 * @param player2Move            the move chosen by player 2
 * @param result                 the evaluated result of the round
 * @param invalidMovePlayerName  if {@code result} is {@link Result#INVALID_MOVE}, the name of the player with invalid move; otherwise empty string
 */
public record RoundInfo(Move player1Move, Move player2Move, Result result, String invalidMovePlayerName) {}
