package org.vladpush.rps.player;

import org.junit.jupiter.api.Test;
import org.vladpush.rps.models.Move;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComputerPlayerTest {

    @Test
    void returnsMoveFromMoveProvider() {
        var player = new ComputerPlayer(new FakeMoveProvider(Move.ROCK));

        assertEquals(Move.ROCK, player.makeMove());
    }
}
