package org.vladpush.rps.provider;

import org.vladpush.rps.models.Move;
import org.vladpush.rps.parser.InputParser;

/**
 * {@link MoveProvider} that obtains a move from an {@link InputParser} (i.e., user input).
 *
 * @param parser input parser used to read and parse moves
 */
public record InputProvider(InputParser parser) implements MoveProvider {
    /**
     * Reads the next move from the underlying {@link InputParser}.
     *
     * @return the parsed move (may be {@link org.vladpush.rps.models.Move#UNKNOWN})
     */
    @Override
    public Move move() {
        return parser.getMove().value();
    }
}
