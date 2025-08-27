package org.vladpush.rps.player;

import org.vladpush.rps.models.Move;
import org.vladpush.rps.provider.MoveProvider;

/**
 * Player implementation that delegates move selection to a {@link MoveProvider} (e.g., random).
 */
public class ComputerPlayer implements Player {
    private final MoveProvider moveProvider;

    /**
     * Creates a computer player.
     *
     * @param moveProvider provider used to generate the computer's move
     */
    public ComputerPlayer(MoveProvider moveProvider) {
        this.moveProvider = moveProvider;
    }

    /** {@inheritDoc} */
    @Override
    public Move makeMove() {
        return moveProvider.move();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return "Computer";
    }
}
