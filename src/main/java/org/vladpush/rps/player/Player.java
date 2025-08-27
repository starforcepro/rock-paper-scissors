package org.vladpush.rps.player;

import org.vladpush.rps.models.Move;

/**
 * Participant of the game capable of making a move and having a display name.
 */
public interface Player {
    /**
     * Produces the player's move for the current step.
     *
     * @return the chosen {@link org.vladpush.rps.models.Move}
     */
    Move makeMove();

    /**
     * Returns a human-readable name for printing.
     *
     * @return player's display name
     */
    String name();
}
