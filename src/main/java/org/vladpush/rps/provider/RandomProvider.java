package org.vladpush.rps.provider;

import org.vladpush.rps.models.Move;

import java.util.Random;

/**
 * {@link MoveProvider} that returns moves at random using a {@link Random} source.
 */
public class RandomProvider implements MoveProvider {
    private final Random random;

    /**
     * Creates a random-based move provider.
     *
     * @param random the randomness source
     */
    public RandomProvider(Random random) {
        this.random = random;
    }

    /**
     * Returns a uniformly random move among Rock, Paper, and Scissors.
     *
     * @return randomly chosen move
     */
    public Move move() {
        var moveIndex = random.nextInt(3);
        return switch (moveIndex) {
            case 0 -> Move.ROCK;
            case 1 -> Move.PAPER;
            case 2 -> Move.SCISSORS;
            default -> throw new IllegalStateException("Unexpected value: " + moveIndex);
        };
    }
}
