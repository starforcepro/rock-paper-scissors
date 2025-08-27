package org.vladpush.rps.provider;

import org.vladpush.rps.models.Move;

/**
 * Strategy for supplying a move, e.g., from user input or randomly.
 */
public interface MoveProvider {
    /**
     * Returns the next move from this provider.
     *
     * @return a {@link org.vladpush.rps.models.Move}
     */
    Move move();
}
