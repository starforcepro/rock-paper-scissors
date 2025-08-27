package org.vladpush.rps.player;

import org.vladpush.rps.models.Move;
import org.vladpush.rps.provider.MoveProvider;

/**
 * Player implementation representing a real human user whose move is provided by a {@link MoveProvider}.
 */
public class RealPlayer implements Player {
    private final MoveProvider moveProvider;

    /**
     * Creates a real (human) player.
     *
     * @param moveProvider provider that supplies the human's move (usually via input)
     */
    public RealPlayer(MoveProvider moveProvider) {
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
        return "Player";
    }
}
