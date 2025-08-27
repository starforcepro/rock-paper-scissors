package org.vladpush.rps.player;

import org.vladpush.rps.models.Move;
import org.vladpush.rps.provider.MoveProvider;

public class FakeMoveProvider implements MoveProvider {
    private final Move move;

    public FakeMoveProvider(Move move) {
        this.move = move;
    }

    @Override
    public Move move() {
        return move;
    }
}
