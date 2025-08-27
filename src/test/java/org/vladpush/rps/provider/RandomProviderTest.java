package org.vladpush.rps.provider;

import org.junit.jupiter.api.Test;
import org.vladpush.rps.models.Move;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomProviderTest {

    @Test
    void returnsAllowedMovesOnCall() {
        RandomProvider provider = new RandomProvider(new Random(12345L));
        var m1 = provider.move();
        var m2 = provider.move();
        var m3 = provider.move();

        assertEquals(Move.PAPER, m1);
        assertEquals(Move.PAPER, m2);
        assertEquals(Move.ROCK, m3);
    }
}
