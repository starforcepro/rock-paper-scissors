package org.vladpush.rps.player;

import org.junit.jupiter.api.Test;
import org.vladpush.rps.models.Move;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RealPlayerTest {

    @Test
    void makeMove_returnsProviderMove() {
        var player = new RealPlayer(new FakeMoveProvider(Move.ROCK));

        assertEquals(Move.ROCK, player.makeMove());
    }
}
